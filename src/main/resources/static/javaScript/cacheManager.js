const CACHE_KEY = "UAT_APP_CACHE_V1";

function saveToCache() {
  const allInputs = document.querySelectorAll("input, textarea");

  const dataToSave = {};

  allInputs.forEach((el) => {
    if (el.id) {
      dataToSave[el.id] = el.value;
    }
  });

  localStorage.setItem(CACHE_KEY, JSON.stringify(dataToSave));

}

document.addEventListener("input", function (e) {
  if (e.target.tagName === "INPUT" || e.target.tagName === "TEXTAREA") {
    saveToCache();
  }
});

function loadFromCache() {
  const savedData = localStorage.getItem(CACHE_KEY);

  if (savedData) {
    const parsedData = JSON.parse(savedData);

    Object.keys(parsedData).forEach((elementId) => {
      const el = document.getElementById(elementId);
      if (el) {
        el.value = parsedData[elementId];
      }
    });

    console.log("Data restored from cache.");
  }
}
