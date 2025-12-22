async function runValidation(id) {
  // 1. AMBIL ELEMEN INPUT
  const urlBox = document.getElementById(`urlEndpoint_${id}`);
  const jsonHeaderBox = document.getElementById(`inputHeader_${id}`);
  const manualGroupBox = document.getElementById(`manualInputGroup_${id}`);
  const bodyInputBox = document.getElementById(`inputBody_${id}`);
  const responseInputBox = document.getElementById(`inputResponse_${id}`);
  const resultBox = document.getElementById(`outputResult_${id}`);

  const inputElements = [urlBox, jsonHeaderBox, bodyInputBox, responseInputBox];

  // 2. RESET STYLE
  inputElements.forEach((el) => el.classList.remove("is-valid", "is-invalid"));
  manualGroupBox
    .querySelectorAll("textarea")
    .forEach((el) => el.classList.remove("is-valid", "is-invalid"));

  // UI Loading
  resultBox.innerHTML =
    '<span class="text-primary spinner-border spinner-border-sm"></span> Validating...';

  // 3. LOGIKA HEADER (MANUAL vs JSON)
  let headerVal = "";
  const isManualMode = jsonHeaderBox.style.display === "none";

  if (isManualMode) {
    const contentType = document.getElementById(`inputContentType_${id}`).value;
    const authorization = document.getElementById(
      `inputAuthorization_${id}`
    ).value;
    const timestamp = document.getElementById(`inputTimestamp_${id}`).value;
    const signature = document.getElementById(`inputSignature_${id}`).value;
    const partnerID = document.getElementById(`inputPartnerID_${id}`).value;
    const externalID = document.getElementById(`inputExternalID_${id}`).value;
    const channelID = document.getElementById(`inputChannelID_${id}`).value;

    const headerObj = {
      "Content-Type": contentType,
      Authorization: authorization,
      "X-TIMESTAMP": timestamp,
      "X-SIGNATURE": signature,
      "X-PARTNER-ID": partnerID,
      "X-EXTERNAL-ID": externalID,
      "CHANNEL-ID": channelID,
    };
    headerVal = JSON.stringify(headerObj, null, 2);
  } else {
    headerVal = jsonHeaderBox.value;
  }

  // Ambil URL & Body & Response
  const urlBoxVal = document.getElementById(`urlEndpoint_${id}`).value;
  const bodyVal = bodyInputBox.value;
  const responseVal = responseInputBox.value;

  // 4. KONFIGURASI API
  const createOptions = (data) => ({
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: data || "{}",
  });

  const urlEndpoint = `http://localhost:8080/${typeAPI}/req/url/case${id}`;
  const urlHeader = `http://localhost:8080/${typeAPI}/req/header/case${id}`;
  const urlBody = `http://localhost:8080/${typeAPI}/req/body/case${id}`;
  const urlResp = `http://localhost:8080/${typeAPI}/resp/case${id}`;

  try {
    // --- 5. EKSEKUSI API (FLOW BARU) ---
    
    // LANGKAH A: Jalankan Body, Header, Response duluan
    const promiseBody = fetch(urlBody, createOptions(bodyVal));
    console.log("Hit Validate Body dengan Request: "+ bodyVal)
    const promiseHeader = fetch(urlHeader, createOptions(headerVal));
    console.log("Hit Validate Header dengan Request: "+headerVal)
    const promiseResponse = fetch(urlResp, createOptions(responseVal));
    console.log("Hit Validate Response dengan Request: "+responseVal)

    // LANGKAH B: Tunggu Hasil BODY (Wajib, untuk dapat detectedService)
    const resBody = await promiseBody;
    const dataBody = await resBody.json(); // Kita butuh isi JSON-nya sekarang

    // LANGKAH C: Ambil Detected Service
    // Default "unknown" jika field tidak ada
    let detectedService = "unknown";
    if (dataBody && dataBody.detectedService) {
        detectedService = dataBody.detectedService;
    }

    // LANGKAH D: Request URL (Menggunakan detectedService dari Body)
    // Payload disesuaikan dengan DTO Java: UrlRequestDto
    const urlRequestPayload = {
        urlEndpoint: urlBoxVal, 
        detectedService: detectedService 
    };
    const urlVal = JSON.stringify(urlRequestPayload, null, 2);

    const resUrl = await fetch(urlEndpoint, createOptions(urlVal));
    console.log("Hit Validate Response dengan Request: "+urlVal)
    
    // LANGKAH E: Tunggu sisanya (Header & Response) selesai
    const resHeader = await promiseHeader;
    const resResponse = await promiseResponse;

    // LANGKAH F: Susun Result Array
    // Urutan Array WAJIB: [URL, Header, Body, Response] agar loop di bawah tidak error
    const results = [
        { httpCode: resUrl.status, data: await resUrl.json() },         // URL (Baru selesai)
        { httpCode: resHeader.status, data: await resHeader.json() },   // Header
        { httpCode: resBody.status, data: dataBody },                   // Body (Sudah ada datanya)
        { httpCode: resResponse.status, data: await resResponse.json() }// Response
    ];

    // 6. LOGIKA PEWARNAAN & FORMATTING
    let logHtml = "";
    let isAllPassed = true;

    results.forEach((item, index) => {
      const type = ["Url", "Header", "Body", "Response"][index];

      // Tentukan elemen input mana yang harus diwarnai
      let currentInput;
      if (index === 1 && isManualMode) {
        currentInput = manualGroupBox;
      } else {
        currentInput = inputElements[index];
      }

      let message = "";
      let colorClass = "";
      let icon = "";

      if (item.httpCode === 200 && item.data.status === "OK") {
        message = item.data.message;
        colorClass = "text-success";
        icon = "✅";
        if (currentInput) currentInput.classList.add("is-valid");
        if (index === 1 && isManualMode) {
          manualGroupBox.classList.remove("border", "border-danger");
        }
      } else {
        isAllPassed = false;
        colorClass = "text-danger";
        icon = "❌";
        if (item.httpCode === 400) message = "Bad Request";
        else if (item.httpCode === 500) message = "Internal Server Error";
        else message = item.data.message || "Unknown Error";
        if (currentInput) currentInput.classList.add("is-invalid");
        if (index === 1 && isManualMode) {
          manualGroupBox.classList.add("border", "border-danger", "p-2", "rounded");
        }
      }

      // Format List
      if (message && typeof message === "string" && message.includes(", ")) {
        const msgList = message.split(", ");
        const listItems = msgList.map((msg) => `<li>${msg}</li>`).join("");
        message = `<ol class="ps-3 mb-0" style="list-style-type: decimal;">${listItems}</ol>`;
      }

      logHtml += `
            <div class="${colorClass} mb-1 border-bottom pb-1">
                <strong>[${type}]</strong> ${icon}<br>
                HTTP ${item.httpCode}: 
                <div class="mt-1">${message}</div>
            </div>
        `;
    });

    // Tombol Simpan
    const btnSave = document.getElementById(`btnSave_${id}`);
    if (isAllPassed) {
      btnSave.disabled = false;
      btnSave.classList.replace("btn-secondary", "btn-success");
    } else {
      btnSave.classList.replace("btn-success", "btn-secondary");
      btnSave.disabled = true;
    }

    // 7. SUMMARY
    let summaryHtml = "";
    if (isAllPassed) {
      summaryHtml = `<div class="alert alert-success py-1 fw-bold text-center">RESULT: PASSED</div>`;
    } else {
      // Tampilkan Detected Service agar user tahu apa yang dibaca oleh sistem
      summaryHtml = `
        <div class="alert alert-danger py-1 fw-bold text-center mb-2">RESULT: FAILED</div>
        <div class="text-center text-muted small mb-3 border-bottom pb-2">
           Detected Service by Body: <span class="fw-bold text-dark">${detectedService}</span>
        </div>
      `;
    }

    resultBox.innerHTML = summaryHtml + logHtml;

  } catch (error) {
    console.error(`[Case ${id}] Error:`, error);
    resultBox.innerHTML = `<div class="text-danger fw-bold">Connection Error: ${error.message}</div>`;
  }
}
