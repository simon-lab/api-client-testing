const path = window.location.pathname;

if (path === '/index.html' || path === '/' || path.endsWith('index.html')) {
    console.log("Timer mati di halaman Login");
} else {
    console.log("Timer aktif!");

    const SESSION_TIMEOUT_MS = 15 * 60 * 1000; 

    let logoutTimer;

    function startLogoutTimer() {
        clearTimeout(logoutTimer);
        logoutTimer = setTimeout(() => {
            alert("Sesi Habis! Anda akan logout otomatis.");
            window.location.href = "/perform_logout";
        }, SESSION_TIMEOUT_MS);
    }

    function resetTimer() {
        startLogoutTimer();
    }

    // Event Listener Aktivitas User
    window.onload = resetTimer;
    document.onmousemove = resetTimer;
    document.onclick = resetTimer;
    document.onscroll = resetTimer;
}