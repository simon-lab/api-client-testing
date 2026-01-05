document.addEventListener("DOMContentLoaded", function () {
  // 1. DAFTAR SEMUA SECTION HALAMAN (Untuk disembunyikan saat pindah menu)
  // Tambahkan ID section baru di sini jika ada halaman baru
  const allSections = [
    "testingArea", 
    "accordionMainGuide", 
    "section-view-user", 
    "section-add-user"
  ];

  // 2. KONFIGURASI MENU NAVIGASI
  // Format: { idTombol: '...', idTargetSection: '...', action: function() {...} }
  const navConfig = [
    {
      btnId: "guideBtn",
      sectionId: "accordionMainGuide",
      action: () => {
        if (typeof loadGuideContent === "function") loadGuideContent();
      },
    },
    {
      btnId: "interbankBtn",
      sectionId: "testingArea",
      action: () => {
        currentCategory = "interbank";
        document.getElementById("pageTitle").innerText = "Interbank Transfer Test";
        if (typeof renderTestCases === "function") renderTestCases();
      },
    },
    {
      btnId: "balanceBtn",
      sectionId: "testingArea",
      action: () => {
        currentCategory = "balance";
        document.getElementById("pageTitle").innerText = "Balance Services Test";
        if (typeof renderTestCases === "function") renderTestCases();
      },
    },
    // --- MENU ADMIN BARU ---
    {
      btnId: "viewUserBtn",
      sectionId: "section-view-user",
      action: () => {
        // Fungsi ini ada di file admin-user.js
        if (typeof loadUsers === "function") loadUsers();
      },
    },
    {
      btnId: "addUserBtn",
      sectionId: "section-add-user",
      action: () => {
        // Tidak ada logic khusus saat buka form, cuma reset form jika perlu
        const form = document.getElementById("addUserForm");
        if(form) form.reset();
      },
    },
  ];

  // 3. LOGIKA UTAMA (LOOPING)
  navConfig.forEach((item) => {
    const btn = document.getElementById(item.btnId);

    if (btn) {
      btn.addEventListener("click", function (e) {
        e.preventDefault();

        // A. Sembunyikan SEMUA section
        allSections.forEach((secId) => {
          const el = document.getElementById(secId);
          if (el) el.classList.add("d-none");
        });

        // B. Munculkan section TARGET
        const target = document.getElementById(item.sectionId);
        if (target) target.classList.remove("d-none");

        // C. Jalankan LOGIC KHUSUS (jika ada)
        if (item.action) item.action();

        // D. Update tampilan SIDEBAR AKTIF
        setActiveSidebar(this);
      });
    }
  });

  // 4. FUNGSI UTILITAS: Toggle Sidebar (Tetap Sama)
  const toggleBtn = document.getElementById("sidebarToggle");
  const body = document.body;
  if (toggleBtn) {
    toggleBtn.addEventListener("click", function () {
      if (window.innerWidth > 768) {
        body.classList.toggle("sidebar-hidden");
      } else {
        body.classList.toggle("sidebar-mobile-show");
      }
    });
  }
});

// 5. FUNGSI UTILITAS: Highlight Menu Aktif
// Dibuat global (di luar DOMContentLoaded) agar bisa dipanggil dari mana saja jika perlu
function setActiveSidebar(activeBtn) {
  // Hapus kelas aktif dari semua tombol sidebar & dropdown items
  document.querySelectorAll(".sidebar .nav-link, .list-group-item").forEach((btn) => {
    btn.classList.remove("bg-primary", "text-white");
    btn.classList.add("text-light"); // Kembalikan ke warna teks default
    
    // Reset style khusus list-group-item (tombol dropdown)
    if(btn.classList.contains("list-group-item")) {
        btn.classList.add("bg-transparent");
    }
  });

  // Tambahkan kelas aktif ke tombol yang diklik
  if (activeBtn) {
    activeBtn.classList.remove("bg-transparent", "text-light");
    activeBtn.classList.add("bg-primary", "text-white");
  }
}