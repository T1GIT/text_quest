import React from "react";
import style from "./sass/label.sass";

class Label extends React.Component {

    constructor(props) {
        super(props);
        this.ref = React.createRef()
    }

    hide = () => {
        this.ref.current.classList.add(style.hidden);
    }

    show = () => {
        this.ref.current.classList.remove(style.hidden);
    }

    render() {
        return <div
            ref={this.ref}
            className={style.label}
        >
            {this.props.text}
        </div>
    }
}

export default Label;