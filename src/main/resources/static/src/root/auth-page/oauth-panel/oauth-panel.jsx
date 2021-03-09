import React from "react";
import style from "./oauth-panel.sass";
import svg_discord from "./oauth-btn/svg/discord.svg"
import svg_git from "./oauth-btn/svg/git.svg"
import svg_google from "./oauth-btn/svg/google.svg"
import svg_vk from "./oauth-btn/svg/vk.svg"
import svg_yandex from "./oauth-btn/svg/yandex.svg"
import OauthBtn from "./oauth-btn/oauth-btn";

class OauthPanel extends React.Component {

    render() {
        return <div className={style.panel}>
            <OauthBtn href={btnHref.vk} svg={svg_vk} text={"ВКонтакте"}/>
            <OauthBtn href={btnHref.yandex} svg={svg_yandex} text={"Яндекс"}/>
            <OauthBtn href={btnHref.yandex} svg={svg_google} text={"Google"}/>
            <OauthBtn href={btnHref.yandex} svg={svg_discord} text={"Discord"}/>
            <OauthBtn href={btnHref.yandex} svg={svg_git} text={"GitHub"}/>
        </div>;
    }
}

export default OauthPanel;