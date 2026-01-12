// --- FUNGSI 1: LOAD USERS (GET) ---
function loadUsers() {
  const tbody = document.getElementById("userTableBody");
  tbody.innerHTML = '<tr><td colspan="5" class="text-center">Loading...</td></tr>';

  fetch("http://10.126.57.48:8080/admin/users")
    .then((response) => {
      if (!response.ok) throw new Error("Gagal mengambil data");
      return response.json();
    })
    .then((users) => {
      let rowsHtml = "";

      users.forEach((user) => {
        let downloadButton = "";
        
        if (user.excelFileName) {
            // Jika ada file, buat link download ke endpoint backend
            // Kita pakai target="_blank" agar download di tab baru/langsung
            downloadButton = `
                <a href="/admin/users/${user.id}/download" class="btn btn-sm btn-success" title="${escapeHtml(user.excelFileName)}">
                    Download Xlsx
                </a>
            `;
        } else {
            // Jika tidak ada file
            downloadButton = `
                <button class="btn btn-sm btn-secondary" disabled>
                    No File
                </button>
            `;
        }

        rowsHtml += `
            <tr>
                <td>${user.id}</td>
                <td>${escapeHtml(user.username)}</td>
                <td>
                    <span class="badge ${user.role === "ROLE_ADMIN" ? "bg-danger" : "bg-primary"}">
                        ${escapeHtml(user.role)}
                    </span>
                </td>
                <td class="text-center">
                    ${downloadButton}
                </td>
                <td>
                    <button class="btn btn-sm btn-outline-danger" onclick="deleteUser(${user.id})">
                        Hapus
                    </button>
                </td>
            </tr>
        `;
        console.log("Berhasil load user:", user.username);
      });
      tbody.innerHTML = rowsHtml;
    })
    .catch((err) => {
      tbody.innerHTML = `<tr><td colspan="5" class="text-danger text-center">Error: ${err.message}</td></tr>`;
    });
}

document.addEventListener("DOMContentLoaded", function () {
  const addUserForm = document.getElementById("addUserForm");
  const cancelBtn = document.querySelector("#addUserForm .btn-secondary");

  if (addUserForm) {
    addUserForm.addEventListener("submit", async function (e) {
      e.preventDefault();

      const usernameInput = document.getElementById("newUsername");
      const passwordInput = document.getElementById("newPassword");
      const roleInput = document.getElementById("newRole");
      const submitBtn = addUserForm.querySelector("button[type='submit']");
      const alertBox = document.getElementById("addUserAlert");

      const userData = {
        username: usernameInput.value,
        password: passwordInput.value,
        role: roleInput.value,
      };

      const originalBtnText = submitBtn.innerText;
      submitBtn.innerText = "Menyimpan...";
      submitBtn.disabled = true;
      alertBox.classList.add("d-none");

      try {
        const response = await fetch("http://10.126.57.48:8080/admin/users", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(userData),
        });

        const result = await response.json();

        if (!response.ok) throw new Error(result.message || "Gagal menyimpan user.");

        console.log("Berhasil tambah user:", userData.username);
        alert("User berhasil ditambahkan!");
        addUserForm.reset();
        
        const viewUserBtnSidebar = document.getElementById("viewUserBtn");
        if (viewUserBtnSidebar) viewUserBtnSidebar.click();

      } catch (error) {
        console.error(error);
        alertBox.innerText = "Error: " + error.message;
        alertBox.classList.remove("d-none", "alert-success");
        alertBox.classList.add("alert-danger", "d-block");
      } finally {
        submitBtn.innerText = originalBtnText;
        submitBtn.disabled = false;
      }
    });
  }

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

async function deleteUser(id) {
  if (confirm("Yakin ingin menghapus user ini?")) {
    try {
      const response = await fetch(`/admin/users/${id}`, {
        method: "DELETE",
      });

      if (response.ok) {
        console.log("Berhasil hapus userId:", id);
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

function escapeHtml(text) {
  if (!text) return text;
  return text
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&#039;");
}