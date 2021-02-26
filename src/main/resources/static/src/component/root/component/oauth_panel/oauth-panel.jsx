import React, {Component} from "react";
import Style from "./sass/oauth-panel.sass";

class OauthPanel extends Component {

    onBtnClick = href => event => {
        console.log(href)
        location.href = href;
    }

    render() {
        return <div className={Style.oauthPanel}>
            <button className={Style.vk} onClick={this.onBtnClick(btnHref.vk)}>
                VK
            </button>
            <button className={Style.yandex} onClick={this.onBtnClick(btnHref.yandex)}>
                YANDEX
            </button>
        </div>;
    }
}

export default OauthPanel;