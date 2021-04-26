import React from "react";
import ReactDOM from "react-dom";
import Root from "./root/root.jsx";
import style from "./sass/index.sass"


window.isAuthorised = isAuthorised
window.error = error
window.isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|BB|PlayBook|IEMobile|Windows Phone|Kindle|Silk|Opera Mini/i.test(navigator.userAgent)

window.login = undefined
window.logout = undefined


if (window.opener) {
    if (isAuthorised) {
        window.opener.login()
        window.opener.focus()
    }
    window.close()
} else {
    document.body.className = style.body
    ReactDOM.render(<Root/>, document.querySelector("#root"));
    if (isAuthorised) {
        window.login()
    }
}

