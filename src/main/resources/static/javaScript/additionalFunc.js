document.addEventListener("DOMContentLoaded", function() {
    fetch('/api/current-user')
        .then(response => {
            if(response.ok) return response.json();
            throw new Error("Not Login");
        })
        .then(userData => {
            const userRole = userData.role;
            console.log("Login sebagai:", userRole);

            document.getElementById('navUsername').innerText = "Halo, " + userData.username;

            const protectedElements = document.querySelectorAll('[data-allow-role]');

            protectedElements.forEach(el => {
                const allowedRole = el.getAttribute('data-allow-role');
                
                if (userRole !== allowedRole) {
                    el.remove(); 
                }
            });
        })
        .catch(error => {
            console.error("Error:", error);
            document.getElementById('navUsername').innerText = "Halo, User";
        });
});