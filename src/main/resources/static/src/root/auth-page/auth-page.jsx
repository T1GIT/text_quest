import React from "react";
import style from "./sass/auth-page.sass";
import OauthPanel from "./oauth-panel/oauth-panel";
import BasicPanel from "./basic-panel/basic-panel";

class AuthPage extends React.Component {

    render() {
        return <div className={style.auth_block}>
            <BasicPanel/>
            <OauthPanel/>
        </div>;
    }
}

export default AuthPage;