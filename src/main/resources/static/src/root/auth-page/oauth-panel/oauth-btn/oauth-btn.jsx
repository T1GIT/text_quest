import React from "react";
import style from "./sass/oauth-btn.sass";
import Svg from "../../../../util/svg";

class OauthBtn extends React.Component {

    constructor(props) {
        super(props);
        this.ref = React.createRef()
    }

    onClick = event => {
        let el = this.ref.current
        el.classList.add(style.loading)
        el.style.width = null
        let params = "" +
            "menubar=no," +
            "location=no," +
            "directories=no," +
            "status=no"
        let oauthWindow = window.open(this.props.href, "oauth_window", params)
        oauthWindow.focus()
    }

    onHover = event => {
        let el = this.ref.current
        if (!el.classList.contains(style.loading)) {
            el.style.width = el.lastChild.offsetWidth + "px"
        }
    }

    onBlur = event => {
        this.ref.current.style.width = null
    }

    reset = () => {
        const el = this.ref.current
        el.style.width = null
        el.classList.remove(style.loading)
    }

    render() {
        return <div
            ref={this.ref}
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