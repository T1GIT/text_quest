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
        this.nodes = {}
        this.nodes.vk = React.createRef()
        this.nodes.yandex = React.createRef()
        this.nodes.google = React.createRef()
        this.nodes.discord = React.createRef()
        this.nodes.git = React.createRef()
    }

    reset = () => {
        for (const k in this.nodes) this.nodes[k].current.reset()
    }

    render() {
        const {vk, yandex, google, discord, git} = btnHref
        return <div
            ref={this.ref}
            className={style.panel}
        >
            <OauthBtn ref={this.nodes.vk} href={vk} svg={svg_vk} text={"ВКонтакте"}/>
            <OauthBtn ref={this.nodes.yandex} href={yandex} svg={svg_yandex} text={"Яндекс"}/>
            <OauthBtn ref={this.nodes.google} href={google} svg={svg_google} text={"Google"}/>
            <OauthBtn ref={this.nodes.discord} href={discord} svg={svg_discord} text={"Discord"}/>
            <OauthBtn ref={this.nodes.git} href={git} svg={svg_git} text={"GitHub"}/>
        </div>;
    }
}

export default OauthPanel;