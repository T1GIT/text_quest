import React from "react";
import style from "./sass/header-btn.sass";

class HeaderBtn extends React.Component {

    constructor(props) {
        super(props);
        this.ref = React.createRef();
    }

    onClick = event => {
        const el = this.ref.current
        el.classList.add(style.select)
        const siblings = (el.nextSibling || el.previousSibling)
        siblings.classList.remove(style.select)
        this.props.onClick(event)
    }

    render() {
        return <span
            ref={this.ref}
            className={
                this.props.defaultSelect
                    ? style.button
                    : `${style.button} ${style.select}`
            }
            onClick={this.onClick}
        >
            {this.props.text}
        </span>
    }
}

export default HeaderBtn;