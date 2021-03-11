import React from "react";
import style from "./oauth-panel.sass";
import svg_discord from "./oauth-btn/svg/discord.svg"
import svg_git from "./oauth-btn/svg/git.svg"
import svg_google from "./oauth-btn/svg/google.svg"
import svg_vk from "./oauth-btn/svg/vk.svg"
import svg_yandex from "./oauth-btn/svg/yandex.svg"
import OauthBtn from "./oauth-btn/oauth-btn";

class OauthPanel extends React.Component {

    constructor(props) {
        super(props);
        this.ref = React.createRef()
    }

    render() {
        const {vk, yandex, google, discord, git} = btnHref
        return <div
            ref={this.ref}
            className={style.panel}
        >
            <OauthBtn href={vk} svg={svg_vk} text={"ВКонтакте"}/>
            <OauthBtn href={yandex} svg={svg_yandex} text={"Яндекс"}/>
            <OauthBtn href={google} svg={svg_google} text={"Google"}/>
            <OauthBtn href={discord} svg={svg_discord} text={"Discord"}/>
            <OauthBtn href={git} svg={svg_git} text={"GitHub"}/>
        </div>;
    }
}

export default OauthPanel;