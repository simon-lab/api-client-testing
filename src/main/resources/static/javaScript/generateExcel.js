// === FUNGSI GENERATE EXCEL UTAMA ===
document
  .getElementById("downloadExcelBtn")
  .addEventListener("click", function () {

    const workbook = XLSX.utils.book_new();

    //PROSES SHEET 1
    const interbankData = interbankTestCases.map((item) => ({
      ...item,
      service: "Interbank Transfer",
      prefix: "8.",
    }));
    const wsInterbank = generateExcelTemplate(interbankData);
    XLSX.utils.book_append_sheet(workbook, wsInterbank, "Interbank Transfer");

    //PROSES SHEET 2
    const balanceData = balanceTestCases.map((item) => ({
      ...item,
      service: "Balance Services",
      prefix: "3.",
    }));
    const wsBalance = generateExcelTemplate(balanceData);
    XLSX.utils.book_append_sheet(workbook, wsBalance, "Balance Services");

    // 4. DOWNLOAD FILE
    const fileName = `UAT_Report_${new Date()
      .toLocaleDateString("id-ID")
      .replace(/\//g, "-")}.xlsx`;
    XLSX.writeFile(workbook, fileName);
  });

let validationResults = {};

// === FUNGSI GENERATE SHEET
function generateExcelTemplate(dataInput) {
  // HEADER METADATA DAN TABEL
  const metadata = [
    ["Skenario dan Hasil Uji Fungsionalitas"],
    [""],
    ["Nama Penyedia Layanan:", "", "", "Bank XYZ (Ganti)"]
    ["Nama Layanan API :", "", "", "Disbursement & Balance"],
    ["Nama Pengguna Layanan API:", "", "", "Client Name"],
    ["Tanggal Pengujian:", "", "", new Date().toLocaleDateString("id-ID")],
    [""],
    [""],
  ];

  // HEADER TABEL
  const tableHeader = [
    "No",
    "Service",
    "Scenario",
    "Expected Result",
    "Request",
    "Response",
    "Result",
    "Notes",
  ];

  let excelRows = [];

  //LOOPING DATA
  dataInput.forEach((item) => {
    const uniqueId = item.prefix + item.id;
    const resultData = validationResults[uniqueId];

    // Default Values
    let cellRequest = "URL Endpoint:\nHeader Request:\nRequest Body:";
    let cellResponse = "Response Body:";
    let cellResult = "";
    let cellNotes = "";

    if (resultData) {
      cellRequest = resultData.request;
      cellResponse = resultData.response;
      cellResult = resultData.result;
      cellNotes = resultData.notes;
    }

    excelRows.push([
      uniqueId,
      item.service,
      `Case ${uniqueId}`,
      item.expected,
      cellRequest,
      cellResponse,
      cellResult,
      cellNotes,
    ]);
  });

  // FINALISASI SHEET
  const finalData = [...metadata, tableHeader, ...excelRows];

  const ws = XLSX.utils.aoa_to_sheet(finalData);

  // Lebar Kolom
  ws["!cols"] = [
    { wch: 5 },
    { wch: 12 },
    { wch: 19 },
    { wch: 19 },
    { wch: 60 },
    { wch: 50 },
    { wch: 10 },
    { wch: 20 },
  ];

  // PENGATURAN MERGE CELLS

  ws["!merges"] = [
    { s: { r: 0, c: 0 }, e: { r: 0, c: 7 } },

    { s: { r: 2, c: 0 }, e: { r: 2, c: 2 } },
    { s: { r: 2, c: 3 }, e: { r: 2, c: 7 } },

    { s: { r: 3, c: 0 }, e: { r: 3, c: 2 } },
    { s: { r: 3, c: 3 }, e: { r: 3, c: 7 } },

    { s: { r: 4, c: 0 }, e: { r: 4, c: 2 } },
    { s: { r: 4, c: 3 }, e: { r: 4, c: 7 } },

    { s: { r: 5, c: 0 }, e: { r: 5, c: 2 } },
    { s: { r: 5, c: 3 }, e: { r: 5, c: 7 } },
  ];

  //STYLING (WARNA, FONT, BOLD)

  // Pendefinisian Style
  const styleJudulUtama = {
    font: { bold: true, sz: 14, name: "Arial" },
    alignment: { horizontal: "center" },
  };

  const styleLabelMetadata = {
    font: { bold: true, sz: 11, name: "Arial" },
    fill: { fgColor: { rgb: "ED7D31" } },
    border: {
      top: { style: "thin" },
      bottom: { style: "thin" },
      left: { style: "thin" },
      right: { style: "thin" },
    },
  };

  const styleDataMetadata = {
    font: { sz: 11, name: "Arial" },
    fill: { fgColor: { rgb: "FCE4D6" } },
    border: {
      top: { style: "thin" },
      bottom: { style: "thin" },
      left: { style: "thin" },
      right: { style: "thin" },
    },
  };

  const styleHeaderTabel = {
    font: { bold: true, name: "Arial" },
    fill: { fgColor: { rgb: "ED7D31" } },
    alignment: { horizontal: "center", vertical: "center", wrapText: true },
    border: {
      top: { style: "thin" },
      bottom: { style: "thin" },
      left: { style: "thin" },
      right: { style: "thin" },
    },
  };

  const styleDataTabel = {
    font: { name: "Arial" },
    alignment: { vertical: "top", wrapText: true },
    border: {
      top: { style: "thin" },
      bottom: { style: "thin" },
      left: { style: "thin" },
      right: { style: "thin" },
    },
  };

  const styleInactiveRow = {
    font: { name: "Arial", color: { rgb: "000000" } },
    fill: { fgColor: { rgb: "FFFF00" } },
    alignment: { vertical: "top", wrapText: true },
    border: {
      top: { style: "thin" },
      bottom: { style: "thin" },
      left: { style: "thin" },
      right: { style: "thin" },
    },
  };

  //Terapkan Style ke Sel
  const range = XLSX.utils.decode_range(ws["!ref"]);

  for (let R = range.s.r; R <= range.e.r; ++R) {
    for (let C = range.s.c; C <= range.e.c; ++C) {
      const cell_address = { c: C, r: R };
      const cell_ref = XLSX.utils.encode_cell(cell_address);

      // Jika sel tidak ada (kosong), kita BUAT sel dummy agar bisa dikasih border
      if (!ws[cell_ref]) {
        ws[cell_ref] = { v: "", t: "s" }; // Buat sel kosong bertipe string
      }

      // Logic implementasi style

      // Baris 1
      if (R === 0) {
        ws[cell_ref].s = styleJudulUtama;
      }

      // Baris 3-6 
      else if (R >= 2 && R <= 5) {
        if (C >= 0 && C <= 2) {
          // Kolom A
          ws[cell_ref].s = styleLabelMetadata;
        }
        if (C >= 3 && C <= 7) {
          // Kolom D
          ws[cell_ref].s = styleDataMetadata;
        }
      }

      // Baris 9 
      else if (R === 8) {
        ws[cell_ref].s = styleHeaderTabel;
      }

      // Baris 10 ke atas 
      else if (R > 8) {
        const dataIndex = R - 9;
        const rowData = dataInput[dataIndex];

        // Cek case Inactive
        if (rowData && rowData.status === "inactive") {
          ws[cell_ref].s = styleInactiveRow;
        } else {
          ws[cell_ref].s = styleDataTabel;

          if (C === 6) {
            const val = ws[cell_ref].v;
            if (val === "PASSED") {
              ws[cell_ref].s = {
                ...styleDataTabel,
                font: { bold: true },
              };
            } else if (val === "FAILED") {
              ws[cell_ref].s = {
                ...styleDataTabel,
                font: { color: { rgb: "9C0006" }, bold: true },
                fill: { fgColor: { rgb: "FFC7CE" } },
              };
            }
          }
        }
      }
    }
  }

  return ws;
}

function saveDataRow(id) {
  // Definisikan ID Unik
  let prefix = currentCategory === "interbank" ? "8." : "3.";
  const uniqueId = prefix + id;

  // Ambil Data Header
  const jsonHeaderBox = document.getElementById(`inputHeader_${id}`);
  const isManualMode = jsonHeaderBox.style.display === "none";
  let headerVal = "";

  if (isManualMode) {
    // Ambil dari input manual
    const headerObj = {
      "Content-Type": document.getElementById(`inputContentType_${id}`).value,
      Authorization: document.getElementById(`inputAuthorization_${id}`).value,
      "X-TIMESTAMP": document.getElementById(`inputTimestamp_${id}`).value,
      "X-SIGNATURE": document.getElementById(`inputSignature_${id}`).value,
      "X-PARTNER-ID": document.getElementById(`inputPartnerID_${id}`).value,
      "X-EXTERNAL-ID": document.getElementById(`inputExternalID_${id}`).value,
      "CHANNEL-ID": document.getElementById(`inputChannelID_${id}`).value,
      "X-USERNAME": document.getElementById(`inputUsername_${id}`)?.value || "",
      "X-PASSWORD": document.getElementById(`inputPassword_${id}`)?.value || "",
    };
    headerVal = JSON.stringify(headerObj, null, 2);
  } else {
    // Ambil langsung dari kotak JSON
    headerVal = jsonHeaderBox.value;
  }

  // Ambil Data Lainnya
  const urlVal = document.getElementById(`urlEndpoint_${id}`).value;
  const bodyVal = document.getElementById(`inputBody_${id}`).value;
  const responseVal = document.getElementById(`inputResponse_${id}`).value;

  // Ambil status result terakhir dari kotak Log (Jika user sudah validate)
  const logBox = document.getElementById(`outputResult_${id}`);
  let statusResult = "NOT TESTED";
  if (logBox.innerHTML.includes("PASSED")) statusResult = "PASSED";
  else if (logBox.innerHTML.includes("FAILED")) statusResult = "FAILED";

  // Format String untuk Excel
  const requestText = `URL Endpoint: ${urlVal}\nHeader Request:\n${headerVal}\nRequest Body:\n${bodyVal}`;
  const responseText = `Response Body:\n${responseVal}`;

  // SIMPAN KE VARIABLE GLOBAL (validationResults)
  validationResults[uniqueId] = {
    request: requestText,
    response: responseText,
    result: statusResult,
    notes:
      statusResult === "PASSED"
        ? "Sesuai Expected Result"
        : "Belum Sesuai / Error",
  };

  // UI Feedback
  const btnSave = document.getElementById(`btnSave_${id}`);
  const originalText = btnSave.innerText;

  btnSave.innerText = "Tersimpan!";
  btnSave.classList.replace("btn-success", "btn-secondary"); // Ganti warna jadi abu

  // Balikin tombol setelah 1.5 detik
  setTimeout(() => {
    btnSave.innerText = "Simpan";
    btnSave.classList.replace("btn-secondary", "btn-success");
  }, 1500);

  console.log(`[Data Saved] Case ${uniqueId} saved to memory.`);
}
