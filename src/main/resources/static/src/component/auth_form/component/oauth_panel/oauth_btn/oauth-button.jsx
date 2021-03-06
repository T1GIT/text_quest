import React from "react";
import style from "./sass/oauth-button.sass";
import Svg from "../../../../svg";

class OauthButton extends React.Component {

    constructor(props) {
        super(props);
        this.elem = React.createRef()
    }

    onClick = href => event => {
        this.elem.current.classList.add(style.loading)
        this.elem.current.style.width = null
        location.href = href;
    }

    onHover = event => {
        const el = this.elem.current
        if (!el.classList.contains(style.loading)) {
            el.style.width = `${el.lastChild.offsetWidth}px`
        }
    }

    onBlur = event => {
        this.elem.current.style.width = null
    }

    render() {
        return <div
            ref={this.elem}
            className={style.button}
            onClick={this.onClick(btnHref[this.props.hrefName])}
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

export default OauthButton;