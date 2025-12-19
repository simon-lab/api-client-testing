// Nama kunci penyimpanan di browser
const CACHE_KEY = "UAT_APP_CACHE_V1";

function saveToCache() {
  // 1. Ambil semua elemen Input dan Textarea di halaman
  const allInputs = document.querySelectorAll("input, textarea");

  const dataToSave = {};

  // 2. Loop dan simpan value berdasarkan ID-nya
  allInputs.forEach((el) => {
    // Hanya simpan jika elemen punya ID (dan bukan type file/password jika perlu)
    if (el.id) {
      dataToSave[el.id] = el.value;
    }
  });

  // 3. Simpan ke LocalStorage sebagai String JSON
  localStorage.setItem(CACHE_KEY, JSON.stringify(dataToSave));

  // (Opsional) Cek di console
  // console.log("Auto-saved:", new Date().toLocaleTimeString());
}

// PASANG EVENT LISTENER (Agar otomatis simpan saat ngetik)
// Kita pasang di 'document' agar mendeteksi input dinamis juga
document.addEventListener("input", function (e) {
  // Cek apakah yang diketik adalah input atau textarea
  if (e.target.tagName === "INPUT" || e.target.tagName === "TEXTAREA") {
    saveToCache();
  }
});

function loadFromCache() {
  // 1. Ambil data dari LocalStorage
  const savedData = localStorage.getItem(CACHE_KEY);

  if (savedData) {
    const parsedData = JSON.parse(savedData);

    // 2. Loop isi data dan masukkan kembali ke form
    // Object.keys mengubah object jadi array kunci (misal: ['partnerID', 'case_1', ...])
    Object.keys(parsedData).forEach((elementId) => {
      const el = document.getElementById(elementId);
      if (el) {
        el.value = parsedData[elementId];
      }
    });

    console.log("Data restored from cache.");
  }
}
