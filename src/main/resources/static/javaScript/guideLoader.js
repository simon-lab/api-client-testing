async function loadGuideContent() {
  const guideContainer = document.getElementById("guideArea");

  if (guideContainer.innerHTML.trim() !== "") {
    return;
  }

  try {
    const response = await fetch("guide.html");

    if (response.ok) {
      const htmlContent = await response.text();

      guideContainer.innerHTML = htmlContent;
      console.log("Guide loaded successfully");
    } else {
      guideContainer.innerHTML =
        "<p class='text-danger'>Gagal memuat panduan.</p>";
    }
  } catch (error) {
    console.error("Error loading guide:", error);
  }
}
const interbankGuides = [
  {
    testCase: "8.1",
    description: `Error Code: 401xx01
Error Message: "Access Token Invalid"

Skenario Negatif: Menguji respon sistem saat Access Token yang digunakan tidak valid atau sudah kadaluwarsa.

Contoh sederhana:
- Mengirimkan request yang salah pada field header Authorization`,
  },
  {
    testCase: "8.2",
    description: `Error Code: 401xx00
Error Message: "Unauthorized Signature"

Skenario Negatif: Menguji respon sistem saat Signature tidak valid atau tidak terotorisasi.

Contoh sederhana:
- Mengirimkan request dengan Signature yang salah pada header Signature`,
  },
  {
    testCase: "8.3",
    description: `Error Code: 400xx02
Error Message: "Invalid Mandatory Field {.......}"

Skenario Negatif: Menguji respon saat terdapat field wajib (mandatory) yang tidak diisi atau hilang dalam request.

Contoh sederhana:
- Mengirimkan request dengan mengosongkan field wajib seperti X-PARTNER-ID.`,
  },
  {
    testCase: "8.4",
    description: `Error Code: 400xx01
Error Message: "Invalid Field Format {.......}"

Skenario Negatif: Menguji respon saat format input pada field tertentu tidak sesuai dengan ketentuan.

Contoh sederhana:
- Mengirimkan request dengan format tanggal pada field transactionDate yang tidak sesuai (DD-YYYY-MMTmm:HH:ss+GMT).`,
  },
  {
    testCase: "8.5",
    description: `Error Code: 409xx00
Error Message: "Conflict"

Skenario Negatif: Menguji respon saat menggunakan X-EXTERNAL-ID yang sama (duplikat) untuk request yang berbeda.

Contoh sederhana:
- Mengirimkan dua request berbeda dengan X-EXTERNAL-ID yang sama.`,
  },
  {
    testCase: "8.6",
    description: `Response Code: 2001600
Response Message: "Successful"

Skenario Positif: Melakukan Eksternal Account Inquiry menggunakan kode SWIFT, CASE INI HANYA DILAKUKAN UNTUK CLIENT BI FAST

Contoh sederhana:
- Mengirimkan request inquiry dengan normal sesuai specification.`,
  },
  {
    testCase: "8.7",
    description: `Response Code: 2001600
Response Message: "Successful"

Skenario Positif: Melakukan Eksternal Account Inquiry menggunakan kode Bank, CASE INI HANYA DILAKUKAN UNTUK CLIENT REALTIME ONLINE

Contoh sederhana:
- Mengirimkan request inquiry dengan normal sesuai specification.`,
  },
//   {
//     testCase: "8.8",
//     description: `Error Code: 4031618
// Error Message: "Inactive Account"

// Skenario Negatif: Menguji respon saat melakukan Inquiry Account ke rekening yang statusnya sudah tutup atau tidak aktif.`,
//   },
  {
    testCase: "8.9",
    description: `Response Code: 2001800
Response Message: "Successful"

Skenario Positif: Melakukan Interbank Trigger Transfer menggunakan kode SWIFT, CASE INI HANYA DILAKUKAN UNTUK CLIENT BI FAST.

Contoh sederhana:
- Mengirimkan request transfer dengan normal sesuai specification.`,
  },
  {
    testCase: "8.10",
    description: `Response Code: 2001800
Response Message: "Successful"

Skenario Positif: Melakukan Interbank Trigger Transfer menggunakan kode Bank, CASE INI HANYA DILAKUKAN UNTUK CLIENT REALTIME ONLINE.

Contoh sederhana:
- Mengirimkan request transfer dengan normal sesuai specification.`,
  },
  {
    testCase: "8.11",
    description: `Error Code: 4031814
Error Message: "Insufficient Fund"

Skenario Negatif: Menguji respon transaksi Interbank Trigger Transfer saat saldo shadow balance pengirim tidak mencukupi (Insufficient Fund).

Contoh sederhana:
- Mengirimkan request transfer dengan amount lebih besar dari saldo shadow balance yang tersedia pada rekening pengirim.`,
  },
  {
    testCase: "8.12",
    description: `Error Code: 4091801
Error Message: "Duplicate Customer Reference Number"

Skenario Negatif: Menguji respon saat terdeteksi penggunaan Customer Reference Number yang duplikat pada transaksi Interbank Transfer.

Contoh sederhana:
- Mengirimkan dua request transfer berbeda dengan Customer Reference Number yang sama.`,
  },
  {
    testCase: "8.13",
    description: `Response Code: 2003600
Response Message: "Successful"

Skenario Positif: Melakukan Inquiry Status atas transaksi Interbank Transfer yang berhasil (serviceCode: "18", latestTransactionStatus: "00").

Contoh sederhana:
- Mengirimkan request inquiry status dengan normal sesuai specification.`,
  },
];

const balanceGuides = [
  {
    testCase: "3.1",
    description: `Error Code: 401xx01
Error Message: "Access Token Invalid"

Skenario Negatif: Menguji respon sistem saat Access Token yang digunakan tidak valid atau sudah kadaluwarsa.

Contoh sederhana:
- Mengirimkan request yang salah pada field header Authorization`,
  },
  {
    testCase: "3.2",
    description: `Error Code: 401xx00
Error Message: "Unauthorized Signature"

Skenario Negatif: Menguji respon sistem saat Signature tidak valid atau tidak terotorisasi.

Contoh sederhana:
- Mengirimkan request dengan Signature yang salah pada header Signature`,
  },
  {
    testCase: "3.3",
    description: `Error Code: 401xx02
Error Message: "Access Token Expired"

Skenario Negatif: Menguji respon sistem saat Access Token yang digunakan sudah kadaluwarsa.

Contoh sederhana:
- Mengirimkan request dengan Access Token yang sudah expired`,
  },
  {
    testCase: "3.4",
    description: `Error Code: 401xx03
Error Message: "Access Token Not Found"

Skenario Negatif: Menguji respon sistem saat Access Token tidak ditemukan.

Contoh sederhana:
- Mengirimkan request tanpa Access Token pada header Authorization`,
  },
  {
    testCase: "3.5",
    description: `Error Code: 409xx00
Error Message: "Conflict"

Skenario Negatif: Menguji respon saat menggunakan X-EXTERNAL-ID yang sama (duplikat) untuk request yang berbeda.

Contoh sederhana:
- Mengirimkan dua request berbeda dengan X-EXTERNAL-ID yang sama.`,
  },
  {
    testCase: "3.6",
    description: `Response Code: 2001100
Response Message: "Successful"

Skenario Positif: Melakukan Balance Inquiry

Contoh sederhana:
- Mengirimkan request inquiry dengan normal sesuai specification.`,
  },
  // {
  //   testCase: "3.7",
  //   description: "Ini penjelasan untuk 8.7",
  // },
  // {
  //   testCase: "3.3",
  //   description: "Ini penjelasan untuk 8.8",
  // },
  // {
  //   testCase: "3.9",
  //   description: "Ini penjelasan untuk 8.9",
  // },
  // {
  //   testCase: "3.10",
  //   description: "Ini penjelasan untuk 8.10",
  // },
  // {
  //   testCase: "3.11",
  //   description: "Ini penjelasan untuk 8.11",
  // },
];

function renderInterbankGuide() {
  const container = document.getElementById("interbankGuideTestCases");
  if (!container) return;

  // ID unik untuk Parent Accordion
  const parentId = "accordionInterbankGuideParent";

  let htmlContent = `<div class="accordion accordion-flush" id="${parentId}">`;

  interbankGuides.forEach((item, index) => {
    // Generate ID unik berdasarkan index array
    const headingId = `headingGuide_${index}`;
    const collapseId = `collapseGuide_${index}`;

    // Mengubah \n (enter) menjadi <br> HTML agar turun baris
    const formattedDescription = item.description
      ? item.description.replace(/\n/g, "<br>")
      : "";

    htmlContent += `
      <div class="accordion-item border-start border-3 ms-3">
        <h2 class="accordion-header" id="${headingId}">
          <button
            class="accordion-button collapsed"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#${collapseId}" 
            aria-expanded="false"
            aria-controls="${collapseId}"
          >
            <strong>Case ${item.testCase}</strong>
          </button>
        </h2>
        <div
          id="${collapseId}"
          class="accordion-collapse collapse"
          aria-labelledby="${headingId}"
          data-bs-parent="#${parentId}"
        >
          <div class="accordion-body small bg-light text-secondary">
            <div style="font-family: monospace;">
                ${formattedDescription}
            </div>
          </div>
        </div>
      </div>
    `;
  });

  htmlContent += `</div>`;

  container.innerHTML = htmlContent;
}

function renderBalanceGuide() {
  const container = document.getElementById("balanceGuideTestCases");
  if (!container) return;

  // ID unik untuk Parent Accordion
  const parentId = "accordionBalanceGuideParent";

  let htmlContent = `<div class="accordion accordion-flush" id="${parentId}">`;

  balanceGuides.forEach((item, index) => {
    // Generate ID unik berdasarkan index array
    const headingId = `headingGuide_${index}`;
    const collapseId = `collapseGuide_${index}`;

    // Mengubah \n (enter) menjadi <br> HTML agar turun baris
    const formattedDescription = item.description
      ? item.description.replace(/\n/g, "<br>")
      : "";

    htmlContent += `
      <div class="accordion-item border-start border-3 ms-3">
        <h2 class="accordion-header" id="${headingId}">
          <button
            class="accordion-button collapsed"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#${collapseId}" 
            aria-expanded="false"
            aria-controls="${collapseId}"
          >
            <strong>Case ${item.testCase}</strong>
          </button>
        </h2>
        <div
          id="${collapseId}"
          class="accordion-collapse collapse"
          aria-labelledby="${headingId}"
          data-bs-parent="#${parentId}"
        >
          <div class="accordion-body small bg-light text-secondary">
            <div style="font-family: monospace;">
                ${formattedDescription}
            </div>
          </div>
        </div>
      </div>
    `;
  });

  htmlContent += `</div>`;

  container.innerHTML = htmlContent;
}

renderInterbankGuide();
renderBalanceGuide();
