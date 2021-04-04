import React from "react";
import style from "./sass/header-btn.sass";
import Component from "../../../../../../util/component";

class HeaderBtn extends Component {

    constructor(props) {
        super(props);
    }

    afterRender() {
        if (this.props.defaultSelect) {
            this.self.classList.add(style.select)
        }
    }

    select = () => this.self.classList.add(style.select)

    unSelect = () => this.self.classList.remove(style.select)

    render() {
        return <span
            ref={this.self}
            className={style.button}
            onClick={this.props.onClick}
        >
            {this.props.text}
        </span>
    }
}

export default HeaderBtn;