import React from "react";
import style from "./oauth-panel.sass";
import svg_discord from "./oauth_btn/svg/discord.svg"
import svg_git from "./oauth_btn/svg/git.svg"
import svg_google from "./oauth_btn/svg/google.svg"
import svg_vk from "./oauth_btn/svg/vk.svg"
import svg_yandex from "./oauth_btn/svg/yandex.svg"
import OauthButton from "./oauth_btn/oauth-button";

class OauthPanel extends React.Component {

    render() {
        return <div className={style.oauthPanel}>
            <OauthButton hrefName={"vk"} svg={svg_vk} text={"ВКонтакте"}/>
            <OauthButton hrefName={"yandex"} svg={svg_yandex} text={"Яндекс"}/>
            <OauthButton hrefName={"google"} svg={svg_google} text={"Google"}/>
            <OauthButton hrefName={"discord"} svg={svg_discord} text={"Discord"}/>
            <OauthButton hrefName={"git"} svg={svg_git} text={"GitHub"}/>
        </div>;
    }
}

export default OauthPanel;