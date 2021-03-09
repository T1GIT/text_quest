import React from "react";
import style from "./sass/header-btn.sass";

class HeaderBtn extends React.Component {

    constructor(props) {
        super(props);
        this.ref = React.createRef();
    }

    componentDidMount() {
        if (this.props.defaultSelect) {
            this.ref.current.classList.add(style.select)
        }
    }

    select = () => this.ref.current.classList.add(style.select)

    unSelect = () => this.ref.current.classList.remove(style.select)

    render() {
        return <span
            ref={this.ref}
            className={style.button}
            onClick={this.props.onClick}
        >
            {this.props.text}
        </span>
    }
}

export default HeaderBtn;