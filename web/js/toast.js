const main = document.getElementById("toast");
function toast( { title = "", message = "", type = "info", duration = 3000 }) {
    if (main) {
        const toast = document.createElement("div");
        const icons = {
            success: 'fa-solid fa-circle-check',
            info: 'fa-solid fa-circle-info',
            warn: 'fa-solid fa-circle-exclamation',
            error: 'fa-solid fa-circle-exclamation'
        };

        const icon = icons[type];

        toast.classList.add("toast", `toast--${type}`);
        toast.style.animation = `slideInLeft ease 4s, fadeOut linear 2s 2s forwards`;//, fadeOut linear 1s ${duration/1000}s forwards
        toast.innerHTML = `
                    <div class="toast__icon">
                        <i class="${icon}"></i>
                    </div>
                    <div class="toast__body">
                        <h3 class="toast__title">${title}</h3>
                        <p class="toast__msg">${message}</p>
                    </div>
                    <div class="toast__close">
                        <i class="fa-solid fa-xmark"></i>
                    </div>
                    `;
        main.appendChild(toast);

        const timeout = setTimeout(function () {
            main.removeChild(toast);
        }, duration);

        toast.onclick = function (e) {
            if (e.target.closest(".toast__close")) {
                main.removeChild(toast);
                clearTimeout(timeout);
            }
        };
}
}

function success(mess) {
    toast({
        title: "Success",
        message: mess,
        type: "success",
        duration: 6000
    });
}

function error(mess) {
    toast({
        title: "Error",
        message: mess,
        type: "error",
        duration: 6000
    });
}