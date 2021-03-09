import React from "react";
import style from "./sass/oauth-btn.sass";
import Svg from "../../../svg";

class OauthBtn extends React.Component {

    constructor(props) {
        super(props);
        this.ref = React.createRef()
    }

    onClick = event => {
        const el = this.ref.current
        el.classList.add(style.loading)
        el.style.width = null
        location.href = this.props.href;
    }

    onHover = event => {
        const el = this.ref.current
        if (!el.classList.contains(style.loading)) {
            el.style.width = `${el.lastChild.offsetWidth}px`
        }
    }

    onBlur = event => {
        this.ref.current.style.width = null
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