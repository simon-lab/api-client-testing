const toggleBtn = document.getElementById("sidebarToggle");

// Fungsi Sidebar Toggle
if (toggleBtn) {
  toggleBtn.addEventListener("click", function () {
    if (window.innerWidth > 768) {
      body.classList.toggle("sidebar-hidden");
    } else {
      body.classList.toggle("sidebar-mobile-show");
    }
  });
}

function setActiveSidebar(activeBtn) {
  document.querySelectorAll(".list-group-item").forEach((btn) => {
    btn.classList.remove("bg-primary", "text-white");
    btn.classList.add("text-light");
  });

  activeBtn.classList.remove("bg-transparent", "text-light");
  activeBtn.classList.add("bg-primary", "text-white");
}

const btnInterbank = document.getElementById("interbankBtn");
const btnBalance = document.getElementById("balanceBtn");
const btnGuide = document.getElementById("guideBtn");

// EVENT LISTENERS
if (btnGuide) {
  btnGuide.addEventListener("click", function (e) {
    e.preventDefault();

    testingArea.classList.add("d-none");
    accordionMainGuide.classList.remove("d-none");

    loadGuideContent();

    setActiveSidebar(this);
  });
}

if (btnInterbank) {
  btnInterbank.addEventListener("click", function (e) {
    e.preventDefault();
    currentCategory = "interbank";
    pageTitle.innerText = "Interbank Transfer Test";
    testingArea.classList.remove("d-none");
    accordionMainGuide.classList.add("d-none");
    renderTestCases(); 
    setActiveSidebar(this);
  });
}

if (btnBalance) {
  btnBalance.addEventListener("click", function (e) {
    e.preventDefault();
    currentCategory = "balance";
    pageTitle.innerText = "Balance Services Test";
    testingArea.classList.remove("d-none");
    accordionMainGuide.classList.add("d-none");
    renderTestCases();
    setActiveSidebar(this);
  });
}

