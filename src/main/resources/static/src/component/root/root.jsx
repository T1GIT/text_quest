import React, {Component} from "react";
import Style from "./root.sass";
import Background from "../background/background";
import OauthPanel from "../oauth_panel/oauth-panel";

class Root extends Component {
    render() {
        return <div className={Style.root}>
            <Background/>
            <OauthPanel/>
        </div>;
    }
}

export default Root;