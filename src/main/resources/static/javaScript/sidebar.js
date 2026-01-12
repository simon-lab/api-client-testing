document.addEventListener("DOMContentLoaded", function () {


  const allSections = [
    "testingArea", 
    "accordionMainGuide", 
    "section-view-user", 
    "section-add-user"
  ];

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

    {
      btnId: "viewUserBtn",
      sectionId: "section-view-user",
      action: () => {
        // file ada di admin-user.js
        if (typeof loadUsers === "function") loadUsers();
      },
    },
    {
      btnId: "addUserBtn",
      sectionId: "section-add-user",
      action: () => {
        const form = document.getElementById("addUserForm");
        if(form) form.reset();
      },
    },
  ];

  navConfig.forEach((item) => {
    const btn = document.getElementById(item.btnId);

    if (btn) {
      btn.addEventListener("click", function (e) {
        e.preventDefault();

        allSections.forEach((secId) => {
          const el = document.getElementById(secId);
          if (el) el.classList.add("d-none");
        });

        const target = document.getElementById(item.sectionId);
        if (target) target.classList.remove("d-none");

        if (item.action) item.action();

        setActiveSidebar(this);
      });
    }
  });

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

function setActiveSidebar(activeBtn) {
  document.querySelectorAll(".sidebar .nav-link, .list-group-item").forEach((btn) => {
    btn.classList.remove("bg-primary", "text-white");
    btn.classList.add("text-light"); 
    
    if(btn.classList.contains("list-group-item")) {
        btn.classList.add("bg-transparent");
    }
  });

  if (activeBtn) {
    activeBtn.classList.remove("bg-transparent", "text-light");
    activeBtn.classList.add("bg-primary", "text-white");
  }
}