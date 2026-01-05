document.addEventListener("DOMContentLoaded", function () {
  const urlParams = new URLSearchParams(window.location.search);
  const errorAlert = document.getElementById("errorAlert");

  if (urlParams.has("error")) {
    errorAlert.innerText = "Username atau Password Salah!";
    errorAlert.classList.remove("d-none");

    cleanUrl();
  }
  
  if (urlParams.has("logout")) {
    errorAlert.innerText = "Anda telah berhasil logout.";
    errorAlert.classList.remove("alert-danger");
    errorAlert.classList.add("alert-success");
    errorAlert.classList.remove("d-none");

    cleanUrl();
  }
});

// Fungsi untuk membersihkan cache field
function cleanUrl() {
    const cleanUri = window.location.pathname;
    window.history.replaceState({}, document.title, cleanUri);
  }