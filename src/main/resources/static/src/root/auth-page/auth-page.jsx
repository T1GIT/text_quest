import React from "react";
import style from "./sass/auth-page.sass";
import OauthPanel from "./oauth-panel/oauth-panel";
import BasicPanel from "./basic-panel/basic-panel";

class AuthPage extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        if (isAuthorised) {
            this.hide()
        }
    }

    show = () => {
        // TODO
    }

    hide = () => {
        // TODO
    }

    render() {
        return <div className={style.auth_block}>
            <BasicPanel/>
            <OauthPanel/>
        </div>;
    }
}

export default AuthPage;