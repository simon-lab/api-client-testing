// === FUNGSI GENERATE EXCEL UTAMA ===
document
  .getElementById("downloadExcelBtn")
  .addEventListener("click", function () {
    // 1. BUAT WORKBOOK (BUKU KERJA)
    const workbook = XLSX.utils.book_new();

    // 2. PROSES SHEET 1: INTERBANK TRANSFER
    // Filter data khusus Interbank (prefix "8.")
    const interbankData = interbankTestCases.map((item) => ({
      ...item,
      service: "Interbank Transfer",
      prefix: "8.",
    }));
    const wsInterbank = generateExcelTemplate(interbankData);
    XLSX.utils.book_append_sheet(workbook, wsInterbank, "Interbank Transfer");

    // 3. PROSES SHEET 2: BALANCE SERVICES
    // Filter data khusus Balance (prefix "3.")
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

// === FUNGSI GENERATE SHEET (Hanya return Sheet, TIDAK Download) ===
function generateExcelTemplate(dataInput) {
  // --- BAGIAN A: METADATA ---
  // Perhatikan: ["Label", "", "Value"]
  // Label di index 0 (Kolom A), Kosong di index 1 (Kolom B), Value di index 2 (Kolom C)
  const metadata = [
    ["Skenario dan Hasil Uji Fungsionalitas"], // Baris 1 (Index 0)
    [""], // Baris 2 (Index 1)
    ["Nama Penyedia Layanan:", "", "", "Bank XYZ (Ganti)"], // Baris 3 (Index 2) -> Value geser ke C
    ["Nama Layanan API :", "", "", "Disbursement & Balance"], // Baris 4 (Index 3) -> Value geser ke C
    ["Nama Pengguna Layanan API:", "", "", "Client Name"], // Baris 5 (Index 4) -> Value geser ke C
    ["Tanggal Pengujian:", "", "", new Date().toLocaleDateString("id-ID")], // Baris 6 (Index 5) -> Value geser ke C
    [""],
    [""],
  ];

  // --- BAGIAN B: HEADER TABEL ---
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

  // --- BAGIAN C: LOOPING DATA ---
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

  // --- BAGIAN D: FINALISASI SHEET ---
  const finalData = [...metadata, tableHeader, ...excelRows];

  // Buat Worksheet
  const ws = XLSX.utils.aoa_to_sheet(finalData);

  // Atur Lebar Kolom

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

  // --- BAGIAN E: PENGATURAN MERGE CELLS ---
  // Format Merge: { s: {r: baris_awal, c: kol_awal}, e: {r: baris_akhir, c: kol_akhir} }
  // Ingat: Index dimulai dari 0. (A=0, B=1, C=2)
  ws["!merges"] = [
    // 1. Judul Utama (A1 sampai H1) -> Baris Index 0
    { s: { r: 0, c: 0 }, e: { r: 0, c: 7 } },

    // 2. Nama Penyedia Layanan (A3 gabung C3) -> Baris Index 2
    { s: { r: 2, c: 0 }, e: { r: 2, c: 2 } },
    { s: { r: 2, c: 3 }, e: { r: 2, c: 7 } },

    // 3. Nama Layanan API (A4 gabung C4) -> Baris Index 3
    { s: { r: 3, c: 0 }, e: { r: 3, c: 2 } },
    { s: { r: 3, c: 3 }, e: { r: 3, c: 7 } },

    // 4. Nama Pengguna (A5 gabung C5) -> Baris Index 4
    { s: { r: 4, c: 0 }, e: { r: 4, c: 2 } },
    { s: { r: 4, c: 3 }, e: { r: 4, c: 7 } },

    // 5. Tanggal Pengujian (A6 gabung C6) -> Baris Index 5
    { s: { r: 5, c: 0 }, e: { r: 5, c: 2 } },
    { s: { r: 5, c: 3 }, e: { r: 5, c: 7 } },
  ];

  // --- BAGIAN E: STYLING (WARNA, FONT, BOLD) ---

  // 1. Definisikan Style
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
    font: { name: "Arial", color: { rgb: "000000" } }, // Text Hitam
    fill: { fgColor: { rgb: "FFFF00" } }, // Background Kuning
    alignment: { vertical: "top", wrapText: true },
    border: {
      top: { style: "thin" },
      bottom: { style: "thin" },
      left: { style: "thin" },
      right: { style: "thin" },
    },
  };

  // 2. Terapkan Style ke Sel
  const range = XLSX.utils.decode_range(ws["!ref"]); // Ambil rentang area sheet

  for (let R = range.s.r; R <= range.e.r; ++R) {
    for (let C = range.s.c; C <= range.e.c; ++C) {
      const cell_address = { c: C, r: R };
      const cell_ref = XLSX.utils.encode_cell(cell_address);

      // Jika sel tidak ada (kosong), kita BUAT sel dummy agar bisa dikasih border
      if (!ws[cell_ref]) {
        ws[cell_ref] = { v: "", t: "s" }; // Buat sel kosong bertipe string
      }

      // --- LOGIKA PENERAPAN STYLE ---

      // A. Baris 1 (Index 0): JUDUL BESAR
      if (R === 0) {
        ws[cell_ref].s = styleJudulUtama;
      }

      // B. Baris 3-6 (Index 2-5): METADATA (Kolom A saja yang Bold)
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

      // C. Baris 9 (Index 8): HEADER TABEL (Biru, Bold, Putih)
      else if (R === 8) {
        ws[cell_ref].s = styleHeaderTabel;
      }

      // D. Baris 10 ke atas (Index > 8): ISI DATA (Border biasa)
      else if (R > 8) {
        // Cari tahu baris ini milik data yang mana?
        // Header ada di baris index 8. Data mulai index 9.
        // Jadi: index data array = R - 9.
        const dataIndex = R - 9;
        const rowData = dataInput[dataIndex];

        // CEK 1: Apakah data ini inactive?
        if (rowData && rowData.status === "inactive") {
          // JIKA INACTIVE: Pakai style kuning untuk SEMUA KOLOM di baris ini
          ws[cell_ref].s = styleInactiveRow;
        } else {
          ws[cell_ref].s = styleDataTabel;

          // (Opsional) Warnai Merah/Hijau di kolom Result (Kolom G / Index 6)
          if (C === 6) {
            const val = ws[cell_ref].v; // Ambil isi text
            if (val === "PASSED") {
              ws[cell_ref].s = {
                ...styleDataTabel,
                font: { bold: true }, // Text Hijau Tua
              };
            } else if (val === "FAILED") {
              ws[cell_ref].s = {
                ...styleDataTabel,
                font: { color: { rgb: "9C0006" }, bold: true }, // Text Merah Tua
                fill: { fgColor: { rgb: "FFC7CE" } }, // Background Merah Muda
              };
            }
          }
        }
      }
    }
  }

  return ws;
}

// Fungsi Khusus Tombol "Simpan"
function saveDataRow(id) {
  // 1. Definisikan ID Unik (Misal: "8.1" atau "3.1")
  // Kita perlu tahu prefixnya (Interbank '8.' atau Balance '3.')
  let prefix = currentCategory === "interbank" ? "8." : "3.";
  const uniqueId = prefix + id;

  // 2. Ambil Data Header (Logic Manual vs JSON)
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

  // 3. Ambil Data Lainnya
  const urlVal = document.getElementById(`urlEndpoint_${id}`).value;
  const bodyVal = document.getElementById(`inputBody_${id}`).value;
  const responseVal = document.getElementById(`inputResponse_${id}`).value;

  // Ambil status result terakhir dari kotak Log (Jika user sudah validate)
  const logBox = document.getElementById(`outputResult_${id}`);
  let statusResult = "NOT TESTED";
  if (logBox.innerHTML.includes("PASSED")) statusResult = "PASSED";
  else if (logBox.innerHTML.includes("FAILED")) statusResult = "FAILED";

  // 4. Format String untuk Excel
  const requestText = `URL Endpoint: ${urlVal}\nHeader Request:\n${headerVal}\nRequest Body:\n${bodyVal}`;
  const responseText = `Response Body:\n${responseVal}`;

  // 5. SIMPAN KE VARIABLE GLOBAL (validationResults)
  // Pastikan variable 'validationResults' sudah dideklarasikan di paling atas file: let validationResults = {};
  validationResults[uniqueId] = {
    request: requestText,
    response: responseText,
    result: statusResult,
    notes:
      statusResult === "PASSED"
        ? "Sesuai Expected Result"
        : "Belum Sesuai / Error",
  };

  // 6. UI Feedback (Kasih tahu user kalau sudah kesimpan)
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
