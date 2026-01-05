// --- FUNGSI 1: LOAD USERS (GET) ---
function loadUsers() {
  const tbody = document.getElementById("userTableBody");
  // Tampilkan Loading
  tbody.innerHTML = '<tr><td colspan="4" class="text-center">Loading...</td></tr>';

  fetch("http://localhost:8080/admin/users")
    .then((response) => {
      if (!response.ok) throw new Error("Gagal mengambil data");
      return response.json();
    })
    .then((users) => {
      // OPTIMASI: Tampung html dalam variable dulu
      let rowsHtml = "";

      users.forEach((user) => {
        rowsHtml += `
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>
                    <span class="badge ${
                      user.role === "ROLE_ADMIN" ? "bg-danger" : "bg-primary"
                    }">
                        ${user.role}
                    </span>
                </td>
                <td>
                    <button class="btn btn-sm btn-outline-danger" onclick="deleteUser(${user.id})">
                        Hapus
                    </button>
                </td>
            </tr>
        `;
      });

      // Masukkan ke tabel sekaligus (Lebih cepat)
      tbody.innerHTML = rowsHtml;
    })
    .catch((err) => {
      tbody.innerHTML = `<tr><td colspan="4" class="text-danger text-center">Error: ${err.message}</td></tr>`;
    });
}

// --- LOGIKA FORM (ADD USER) ---
document.addEventListener("DOMContentLoaded", function () {
  const addUserForm = document.getElementById("addUserForm");
  const cancelBtn = document.querySelector("#addUserForm .btn-secondary");

  // --- LOGIKA 1: HANDLE SUBMIT FORM ---
  if (addUserForm) {
    addUserForm.addEventListener("submit", async function (e) {
      e.preventDefault();

      // A. Ambil Elemen
      const usernameInput = document.getElementById("newUsername");
      const passwordInput = document.getElementById("newPassword");
      const roleInput = document.getElementById("newRole");
      const submitBtn = addUserForm.querySelector("button[type='submit']");
      const alertBox = document.getElementById("addUserAlert");

      // B. Siapkan Data JSON
      const userData = {
        username: usernameInput.value,
        password: passwordInput.value,
        role: roleInput.value,
      };

      // C. UI Loading
      const originalBtnText = submitBtn.innerText;
      submitBtn.innerText = "Menyimpan...";
      submitBtn.disabled = true;
      alertBox.classList.add("d-none");

      try {
        const response = await fetch("http://localhost:8080/admin/users", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(userData),
        });

        const result = await response.json();

        if (!response.ok) throw new Error(result.message || "Gagal menyimpan user.");

        // --- SKENARIO SUKSES ---
        alert("User berhasil ditambahkan!");
        addUserForm.reset();
        
        // Trigger pindah ke tab View User
        const viewUserBtnSidebar = document.getElementById("viewUserBtn");
        if (viewUserBtnSidebar) viewUserBtnSidebar.click();

      } catch (error) {
        // --- SKENARIO GAGAL ---
        console.error(error);
        alertBox.innerText = "Error: " + error.message;
        alertBox.classList.remove("d-none", "alert-success");
        alertBox.classList.add("alert-danger", "d-block");
      } finally {
        // Reset Tombol (Pakai finally agar jalan saat sukses maupun gagal)
        submitBtn.innerText = originalBtnText;
        submitBtn.disabled = false;
      }
    });
  }

  // --- LOGIKA 2: TOMBOL BATAL ---
  if (cancelBtn) {
    cancelBtn.addEventListener("click", function () {
      addUserForm.reset();
      const alertBox = document.getElementById("addUserAlert");
      if(alertBox) alertBox.classList.add("d-none");

      const viewUserBtnSidebar = document.getElementById("viewUserBtn");
      if (viewUserBtnSidebar) viewUserBtnSidebar.click();
    });
  }
});

// --- FUNGSI BARU: DELETE USER ---
// Harus di luar DOMContentLoaded agar bisa dipanggil oleh onclick HTML
async function deleteUser(id) {
  if (confirm("Yakin ingin menghapus user ini?")) {
    try {
      const response = await fetch(`/admin/users/${id}`, {
        method: "DELETE",
      });

      if (response.ok) {
        alert("User berhasil dihapus");
        loadUsers(); // Refresh tabel otomatis
      } else {
        alert("Gagal menghapus user");
      }
    } catch (error) {
      console.error(error);
      alert("Error koneksi ke server");
    }
  }
}