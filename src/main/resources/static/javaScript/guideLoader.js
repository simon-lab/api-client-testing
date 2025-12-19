// --- FUNGSI LOAD HTML EKSTERNAL ---
async function loadGuideContent() {
  const guideContainer = document.getElementById("guideArea");

  // Cek apakah konten sudah pernah diload? Kalau sudah ada isinya, jangan load lagi (biar hemat)
  if (guideContainer.innerHTML.trim() !== "") {
    return;
  }

  try {
    // Ambil file guide.html
    const response = await fetch("guide.html");

    if (response.ok) {
      // Ubah jadi text
      const htmlContent = await response.text();
      // Masukkan ke dalam div
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
    description: 'Ini penjelasan untuk 8.1"',
  },
  {
    testCase: "8.2",
    description: 'Ini penjelasan untuk 8.2"',
  },
  {
    testCase: "8.3",
    description: "Ini penjelasan untuk 8.3",
  },
  {
    testCase: "8.4",
    description: "Ini penjelasan untuk 8.4",
  },
  {
    testCase: "8.5",
    description: "Ini penjelasan untuk 8.5",
  },
  {
    testCase: "8.6",
    description: "Ini penjelasan untuk 8.6",
  },
  {
    testCase: "8.7",
    description: "Ini penjelasan untuk 8.7",
  },
  {
    testCase: "8.8",
    description: "Ini penjelasan untuk 8.8",
  },
  {
    testCase: "8.9",
    description: "Ini penjelasan untuk 8.9",
  },
  {
    testCase: "8.10",
    description: "Ini penjelasan untuk 8.10",
  },
  {
    testCase: "8.11",
    description: "Ini penjelasan untuk 8.11",
  },
  {
    testCase: "8.12",
    description: "Ini penjelasan untuk 8.12",
  },
  {
    testCase: "8.13",
    description: "Ini penjelasan untuk 8.13",
  },
];

const balanceGuides = [
  {
    testCase: "3.1",
    description: 'Ini penjelasan untuk 3.1"',
  },
  {
    testCase: "3.2",
    description: 'Ini penjelasan untuk 3.2"',
  },
  {
    testCase: "3.3",
    description: "Ini penjelasan untuk 8.3",
  },
  {
    testCase: "3.4",
    description: "Ini penjelasan untuk 8.4",
  },
  {
    testCase: "3.5",
    description: "Ini penjelasan untuk 8.5",
  },
  {
    testCase: "3.6",
    description: "Ini penjelasan untuk 8.6",
  },
  {
    testCase: "3.7",
    description: "Ini penjelasan untuk 8.7",
  },
  {
    testCase: "3.3",
    description: "Ini penjelasan untuk 8.8",
  },
  {
    testCase: "3.9",
    description: "Ini penjelasan untuk 8.9",
  },
  {
    testCase: "3.10",
    description: "Ini penjelasan untuk 8.10",
  },
  {
    testCase: "3.11",
    description: "Ini penjelasan untuk 8.11",
  },
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

    // Format deskripsi: ubah \n (enter) menjadi <br> HTML agar turun baris
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

  htmlContent += `</div>`; // Tutup div parent

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

    // Format deskripsi: ubah \n (enter) menjadi <br> HTML agar turun baris
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

  htmlContent += `</div>`; // Tutup div parent

  container.innerHTML = htmlContent;
}

// Panggil fungsi saat halaman dimuat
renderInterbankGuide();
renderBalanceGuide();
