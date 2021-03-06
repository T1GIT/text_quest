import React from "react";
import style from "./root.sass";
import Background from "../background/background";
import OauthPanel from "../auth_form/component/oauth_panel/oauth-panel";

class Root extends React.Component {
    render() {
        return <div className={style.root}>
            <Background/>
            <OauthPanel/>
        </div>;
    }
}

export default Root;