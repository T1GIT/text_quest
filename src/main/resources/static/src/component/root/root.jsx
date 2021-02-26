import React, {Component} from "react";
import Style from "./sass/root.sass";
import Background from "./component/background/background";
import OauthPanel from "./component/oauth_panel/oauth-panel";

class Root extends Component {
    render() {
        return <div className={Style.root}>
            <Background/>
            <OauthPanel/>
        </div>;
    }
}

export default Root;