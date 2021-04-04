import React from "react";
import style from "./oauth-panel.sass";
import svg_discord from "./oauth-btn/svg/discord.svg"
import svg_git from "./oauth-btn/svg/git.svg"
import svg_google from "./oauth-btn/svg/google.svg"
import svg_vk from "./oauth-btn/svg/vk.svg"
import svg_yandex from "./oauth-btn/svg/yandex.svg"
import OauthBtn from "./oauth-btn/oauth-btn";
import Component from "../../../../util/component";
import axios from "axios";

class OauthPanel extends Component {

    constructor(props) {
        super(props);
        this.nodes.vk = React.createRef()
        this.nodes.yandex = React.createRef()
        this.nodes.google = React.createRef()
        this.nodes.discord = React.createRef()
        this.nodes.git = React.createRef()
    }

    afterRender() {
        axios.post("/auth/url"
        ).then(response => {
            for (let provider in response.data) {
                this.nodes[provider].setHref(response.data[provider])
            }
            this.self.classList.add(style.loaded)
        }).catch(error => {
            console.error(error)
        })
    }

    render() {
        return <div
            ref={this.self}
            className={style.panel}
        >
            <OauthBtn ref={this.nodes.vk} svg={svg_vk} text={"ВКонтакте"}/>
            <OauthBtn ref={this.nodes.yandex} svg={svg_yandex} text={"Яндекс"}/>
            <OauthBtn ref={this.nodes.google} svg={svg_google} text={"Google"}/>
            <OauthBtn ref={this.nodes.discord} svg={svg_discord} text={"Discord"}/>
            <OauthBtn ref={this.nodes.git} svg={svg_git} text={"GitHub"}/>
        </div>;
    }
}

export default OauthPanel;