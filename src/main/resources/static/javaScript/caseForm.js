const body = document.body;

// Fungsi Auto Resize Textarea (Agar form memanjang otomatis)
// Kita gunakan 'delegation' agar textarea yang baru digenerate juga kena efeknya
document.addEventListener("input", function (e) {
  if (
    e.target.tagName === "TEXTAREA" &&
    e.target.classList.contains("form-control")
  ) {
    e.target.style.height = "auto";
    e.target.style.height = e.target.scrollHeight + "px";
  }
});

// DATA DRIVEN TESTING (Generator HTML)

const interbankTestCases = [
  {
    id: "1",
    expected: 'Error Code: 401xx01\nError Message: "Access Token Invalid"',
    status: "active",
  },
  {
    id: "2",
    expected: 'Error Code: 401xx00\nError Message: "Unauthorized Signature"',
    status: "active",
  },
  {
    id: "3",
    expected:
      'Error Code: 400xx02\nError Message: "Invalid Mandatory Fields {.....}"',
    status: "active",
  },
  {
    id: "4",
    expected:
      'Error Code: 400xx01\nError Message: "Invalid Field Format {.....}"',
    status: "active",
  },
  {
    id: "5",
    expected: 'Error Code: 409xx00\nError Message: "Conflict"',
    status: "active",
  },
  {
    id: "6",
    expected: 'Response Code: 2001600\nResponse Message: "Successful"',
    status: "active",
  },
  {
    id: "7",
    expected: 'Response Code: 2001600\nResponse Message: "Successful"',
    status: "active",
  },
  {
    id: "8",
    expected: 'Error Code: 4031618\nError Message: "Inactive Account"',
    status: "active",
  },
  {
    id: "9",
    expected: 'Response Code: 2001800\nResponse Message: "Successful"',
    status: "active",
  },
  {
    id: "10",
    expected: 'Response Code: 2001800\nResponse Message: "Successful"',
    status: "active",
  },
  {
    id: "11",
    expected: 'Error Code: 4031814\nError Message: "Insufficient Fund"',
    status: "active",
  },
  {
    id: "12",
    expected:
      'Error Code: 4091801\nError Message: "Duplicate Customer Reference Number"',
    status: "active",
  },
  {
    id: "13",
    expected: 'Error Code: 2003600\nError Message: "Successful"',
    status: "active",
  },
];

const balanceTestCases = [
  {
    id: "1",
    expected: 'Error Code: 401xx01\nError Message: "Access Token Invalid"',
    status: "active",
  },
  {
    id: "2",
    expected: 'Error Code: 401xx00\nError Message: "Unauthorized Signature"',
    status: "active",
  },
  {
    id: "3",
    expected:
      'Error Code: 400xx02\nError Message: "Invalid Mandatory Fields {.....}"',
    status: "active",
  },
  {
    id: "4",
    expected:
      'Error Code: 400xx01\nError Message: "Invalid Field Format {.....}"',
    status: "active",
  },
  {
    id: "5",
    expected: 'Error Code: 409xx00\nError Message: "Conflict"',
    status: "active",
  },
  {
    id: "6",
    expected: 'Response Code: 2001100\nResponse Message: "Successful"',
    status: "active",
  },
  {
    id: "7",
    expected: 'Response Code: 2001100\nResponse Message: "Successful"',
    status: "active",
  },
  {
    id: "8",
    expected: 'Response Code: 4031118\nError Message: "Account Inactive"',
    status: "inactive",
  },
  {
    id: "9",
    expected:
      'Response Code: 4011100\nResponse Message: " Invalid Access Token Scope"',
    status: "inactive",
  },
  {
    id: "10",
    expected: 'Response Code: 4031105\nResponse Message: "Do Not Honor"',
    status: "inactive",
  },
  {
    id: "11",
    expected: 'Error Code: 4011102\nError Message: "Invalid Customer Token"',
    status: "inactive",
  },
];

// --- STATE MANAGEMENT ---
// Default category
let currentCategory = "interbank";

// --- DOM ELEMENTS ---
const container = document.getElementById("testCaseContainer");

// Listener Global Input (Partner & Channel ID)
// Script ini akan mengupdate semua input case secara otomatis saat Anda mengetik di input global
const globalInputs = ["partnerId", "channelId"];

globalInputs.forEach((id) => {
  const el = document.getElementById(id);
  if (el) {
    el.addEventListener("input", function () {
      // Tentukan target ID prefix berdasarkan input global mana yang diketik
      const targetPrefix =
        id === "partnerId" ? "inputPartnerID_" : "inputChannelID_";

      // Update semua textarea yang relevan di dalam list case
      document
        .querySelectorAll(`[id^="${targetPrefix}"]`)
        .forEach((textarea) => {
          textarea.value = this.value;
        });
    });
  }
});

let typeAPI = "";
function renderTestCases() {
  if (!container) return;

  // 1. Determine which data to use based on state
  let dataToRender = [];
  let prefix = "";
  let title = "";

  const partnerElem = document.getElementById("partnerId");
  const channelElem = document.getElementById("channelId");

  const partnerId = partnerElem ? partnerElem.value : "";
  const channelId = channelElem ? channelElem.value : "";

  if (currentCategory === "interbank") {
    dataToRender = interbankTestCases;
    prefix = "8.";
    title = "Interbank Transfer Test";
    typeAPI = "interbank";
  } else {
    dataToRender = balanceTestCases;
    prefix = "3.";
    title = "Balance Services Test";
    typeAPI = "balance";
  }

  console.log(`Ini TypeAPI nya:`, typeAPI);
  // 2. Generate HTML
  let allHtml = "";

  dataToRender.forEach((item) => {
    // Jika status inactive, skip (jangan buat HTML-nya)
    if (item.status === "inactive") {
      return;
    }

    allHtml += `
    <div class="row"">
                <div class="col-12 col-md-1 d-flex align-items-center justify-content-left fw-bold">
                Case ${prefix}${item.id}
            </div>
            
            <div class="col-12 col-md-8 d-flex align-items-center justify-content-left fw-bold mb-3">
            <div class="me-3">
            <label class="form-label fw-bold text-nowrap">Url Endpoint:</label>
            </div>
                <textarea class="form-control" id="urlEndpoint_${item.id}" rows="1"></textarea>
            </div>
            </div>
            </div>
        <div class="row border-bottom pb-4 mb-4" id="caseRow_${item.id}">

            <div class="col-12 col-md-2 mb-3">
                <label class="form-label fw-bold">Expected Result:</label>
                <div class="small">
                    <textarea class="form-control bg-light" rows="4" disabled>${item.expected}</textarea>
                </div>
            </div>

            <div class="col-12 col-md-3 mb-3">
                <div class="d-flex justify-content-between align-items-center mb-2">
                    <label class="form-label fw-bold mb-0">Header Request:</label>
                    <div class="d-flex gap-2">
                        <span id="jsonOption_${item.id}" onclick="toggleMode('${item.id}', 'json')" class="btn badge rounded-pill bg-primary" style="cursor:pointer;">Json</span>
                        <span id="manualOption_${item.id}" onclick="toggleMode('${item.id}', 'manual')" class="btn badge rounded-pill bg-secondary" style="cursor:pointer;">Manual</span>
                    </div>
                </div>
                
                <textarea id="inputHeader_${item.id}" class="form-control" rows="4" placeholder="Header JSON"></textarea>

                <div id="manualInputGroup_${item.id}" class="manual-input-group mt-2" hidden>
                <div class="manual-input-group">
            <div class="row align-items-center mb-1">
              <label
                for="inputContentType"
                class="col-4 col-form-label fw-bold text-nowrap"
              >
                Content-type:
              </label>
              <div class="col-8">
                <textarea
                  id="inputContentType_${item.id}"
                  class="form-control manual-form"
                  rows="1"
                  disabled
                >application/json</textarea>
              </div>
            </div>

            <div class="row align-items-center mb-1">
              <label
                for="inputAuthorization"
                class="col-4 col-form-label fw-bold text-nowrap"
              >
                Authorization:
              </label>
              <div class="col-8">
                <textarea
                  id="inputAuthorization_${item.id}"
                  class="form-control manual-form"
                  rows="1"
                  placeholder="Masukkan Authorization"
                ></textarea>
              </div>
            </div>

            <div class="row align-items-center mb-1">
              <label
                for="inputTimestamp"
                class="col-4 col-form-label fw-bold text-nowrap"
              >
                X-TIMESTAMP:
              </label>
              <div class="col-8">
                <textarea
                  id="inputTimestamp_${item.id}"
                  class="form-control manual-form"
                  rows="1"
                  placeholder="Masukkan X-TIMESTAMP"
                ></textarea>
              </div>
            </div>

            <div class="row align-items-center mb-1">
              <label
                for="inputSignature"
                class="col-4 col-form-label fw-bold text-nowrap"
              >
                X-SIGNATURE:
              </label>
              <div class="col-8">
                <textarea
                  id="inputSignature_${item.id}"
                  class="form-control manual-form"
                  rows="1"
                  placeholder="Masukkan X-SIGNATURE"
                ></textarea>
              </div>
            </div>

            <div class="row align-items-center mb-1">
              <label
                for="inputPartnerID"
                class="col-4 col-form-label fw-bold text-nowrap"
              >
                X-PARTNER-ID:
              </label>
              <div class="col-8">
                <textarea
                  id="inputPartnerID_${item.id}"
                  class="form-control manual-form"
                  rows="1"
                  placeholder="Masukkan X-PARTNER-ID"
                  disabled
                >${partnerId}</textarea>
              </div>
            </div>

            <div class="row align-items-center mb-1">
              <label
                for="inputExternalID"
                class="col-4 col-form-label fw-bold text-nowrap"
              >
                X-EXTERNAL-ID:
              </label>
              <div class="col-8">
                <textarea
                  id="inputExternalID_${item.id}"
                  class="form-control manual-form"
                  rows="1"
                  placeholder="Masukkan X-EXTERNAL-ID"
                ></textarea>
              </div>
            </div>

            <div class="row align-items-center mb-1">
              <label
                for="inputChannelID"
                class="col-4 col-form-label fw-bold text-nowrap"
              >
                CHANNEL-ID:
              </label>
              <div class="col-8">
                <textarea
                  id="inputChannelID_${item.id}"
                  class="form-control manual-form"
                  rows="1"
                  placeholder="Masukkan CHANNEL-ID"
                  disabled
                >${channelId}</textarea>
              </div>
            </div>
          </div>
                </div>
            </div>

            <div class="col-12 col-md-2 mb-3">
                <label class="form-label fw-bold">Body Request:</label>
                <textarea id="inputBody_${item.id}" class="form-control" rows="4" placeholder="Body JSON"></textarea>
            </div>

            <div class="col-12 col-md-2 mb-3">
                <label class="form-label fw-bold small">Response:</label>
                <textarea id="inputResponse_${item.id}" class="form-control" rows="4" placeholder="Response API"></textarea>
            </div>

            <div class="col-12 col-md-1 align-items-center mb-3 mt-5">
                <button type="button" onclick="runValidation('${item.id}')" class="btn btn-primary w-100">Validate</button>
                <button type="button" onclick="saveDataRow('${item.id}')" id="btnSave_${item.id}" class="btn btn-secondary w-100 mt-1" disabled>Simpan</button>
            </div>

            <div class="col-12 col-md-2 mb-3">
                 <label class="form-label fw-bold small">Validate Result:</label>
                 <div id="outputResult_${item.id}" class="form-control bg-light" style="min-height: 120px; height: auto; overflow-y: auto; font-size: 0.8rem;"></div>
            </div>
        </div>
        `;
  });

  container.innerHTML = allHtml;
}

// --- INIT SCRIPT ---
document.addEventListener("DOMContentLoaded", function () {
  // 1. Render dulu kotak-kotak inputnya (Interbank/Balance)
  renderTestCases();

  // 2. Baru isi datanya dari cache
  loadFromCache();
});

// LOGIC INTERAKSI

// Fungsi Toggle Button Manual/JSON
function toggleMode(id, mode) {
  const boxJson = document.getElementById(`inputHeader_${id}`);
  const boxManual = document.getElementById(`manualInputGroup_${id}`);
  const btnJson = document.getElementById(`jsonOption_${id}`);
  const btnManual = document.getElementById(`manualOption_${id}`);

  if (mode === "manual") {
    boxJson.style.display = "none";
    boxManual.removeAttribute("hidden");

    // Ubah Warna
    btnManual.classList.replace("bg-secondary", "bg-primary");
    btnJson.classList.replace("bg-primary", "bg-secondary");
  } else {
    boxJson.style.display = "block";
    boxManual.setAttribute("hidden", true);

    // Ubah Warna
    btnJson.classList.replace("bg-secondary", "bg-primary");
    btnManual.classList.replace("bg-primary", "bg-secondary");
  }
}

// Listener agar tombol save mati saat user mengetik ulang
document
  .getElementById(`testCaseContainer`)
  .addEventListener("input", function (e) {
    // Cek apakah yang diketik adalah input form (textarea)
    if (e.target.tagName === "TEXTAREA") {
      // Cari ID case-nya (misal: inputBody_1 -> ambil "1")
      const parts = e.target.id.split("_");
      if (parts.length > 1) {
        const id = parts[1];
        const btnSave = document.getElementById(`btnSave_${id}`);

        // Matikan tombol simpan karena data berubah (Butuh validasi ulang)
        if (btnSave) {
          btnSave.disabled = true;
          btnSave.classList.replace("btn-success", "btn-secondary");
        }
      }
    }
  });
