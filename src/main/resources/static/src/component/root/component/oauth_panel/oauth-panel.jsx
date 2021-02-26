import React, {Component} from "react";
import Style from "./sass/oauth-panel.sass";

class OauthPanel extends Component {

    onBtnClick = href => event => {
        location.href = href;
    }

    render() {
        return <div className={Style.oauthPanel}>
            <button className={Style.vk} onClick={this.onBtnClick(btnHref.vk)}>
                VK
            </button>
        </div>;
    }
}

export default OauthPanel;