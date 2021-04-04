import React from "react";
import style from "./sass/oauth-btn.sass";
import panelStyle from "../oauth-panel.sass"
import Svg from "../../../../util/svg";
import Component from "../../../../util/component";

class OauthBtn extends Component {

    constructor(props) {
        super(props);
        this.href = undefined;
    }

    afterRender() {
        this.self.classList.add(panelStyle.oauth_btn)
    }

    setHref = href => this.href = href

    onClick = event => {
        if (this.href) {
            this.self.classList.add(style.loading)
            this.self.style.width = null
            setTimeout(this.openWindow, 200)
        }
    }

    openWindow = () => {
        let params = "" +
            "width=500" +
            "height=600" +
            "menubar=no," +
            "location=no," +
            "directories=no," +
            "status=no"
        let oauthWindow = window.open(this.href, "oauth_window", params)
        oauthWindow.focus()
    }

    onHover = event => {
        if (!this.self.classList.contains(style.loading)) {
            this.self.style.width = this.self.lastChild.offsetWidth + "px"
        }
    }

    onBlur = event => {
        this.self.style.width = null
    }

    reset = () => {
        this.self.style.width = null
        this.self.classList.remove(style.loading)
    }

    render() {
        return <div
            ref={this.self}
            className={style.button}
            onClick={this.onClick}
            onMouseEnter={this.onHover}
            onMouseLeave={this.onBlur}
        >
            <div className={style.wrap}>
                <Svg className={style.svg} svg={this.props.svg}/>
                <div className={style.text}>
                    {this.props.text}
                </div>
            </div>
        </div>
    }
}

export default OauthBtn;