import React from "react";
import style from "./root.sass";
import Background from "./background/background";
import AuthPage from "./auth-page/auth-page";

class Root extends React.Component {
    render() {
        return <div className={style.root}>
            <Background/>
            <AuthPage/>
        </div>;
    }
}

export default Root;