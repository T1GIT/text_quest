import React from "react";
import ReactDOM from "react-dom";
import Root from "./root/root.jsx";
import style from "./sass/index.sass"


window.isAuthorised = isAuthorised

window.login = undefined
window.logout = undefined


if (!window.opener) {
    document.body.className = style.body
    ReactDOM.render(<Root/>, document.querySelector("#root"));
} else if (isAuthorised) {
    window.opener.login()
    window.opener.focus()
    window.close()
}

