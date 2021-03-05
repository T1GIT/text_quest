import React, {Component} from "react";
import Style from "./oauth-panel.sass";
import OauthButton from "./component/oauth-button";

class OauthPanel extends Component {

    onBtnClick = href => event => {
        console.log(href)
        location.href = href;
    }

    render() {
        return <div className={Style.oauthPanel}>
            <OauthButton hrefName={"vk"} text={"ВКонтакте"} className={Style.vk}/>
            <OauthButton hrefName={"yandex"} text={"Яндекс"} className={Style.yandex}/>
            <OauthButton hrefName={"google"} text={"Google"} className={Style.google}/>
            <OauthButton hrefName={"discord"} text={"Discord"} className={Style.discord}/>
            <OauthButton hrefName={"git"} text={"GitHub"} className={Style.git}/>
        </div>;
    }
}

export default OauthPanel;