import React from "react";
import style from "./sass/oauth-btn.sass";
import Svg from "../../../../util/svg";
import Component from "../../../../util/component";

class OauthBtn extends Component {

    constructor(props) {
        super(props);
        this.href = undefined;
    }

    setHref = href => {
        $(this.self).addClass(style.loaded)
        return this.href = href;
    }

    onClick = event => {
        if (window.isMobile) {
            // TODO: Add mobile loading
            document.location.href = this.href
        } else {
            let el = $(this.self)
            el.css({width: ""}).addClass(style.pc_loading)
            setTimeout(this.openWindow, 100)
        }
    }

    openWindow = () => {
        let params = "" +
            "width=500," +
            "height=600," +
            "top=0," +
            "left=0," +
            "menubar=false," +
            "location=false," +
            "directories=false," +
            "status=false"
        window.open(this.href, "oauth_window", params).focus()
    }

    onHover = event => {
        if (!window.isMobile) {
            let el = $(this.self)
            if (!el.hasClass(style.pc_loading)) {
                el.css({width: el.children()[0].offsetWidth + "px"})
            }
        }
    }

    onBlur = event => {
        if (!window.isMobile) {
            $(this.self).css({width: ""})
        }
    }

    reset = () => {
        if (window.isMobile) {
            // TODO: Add mobile loading
        } else {
            let el = $(this.self)
            el.css({width: ""})
            el.removeClass(style.pc_loading)
        }
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