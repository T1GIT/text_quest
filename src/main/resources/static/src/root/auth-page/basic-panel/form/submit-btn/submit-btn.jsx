import React from "react";
import style from "./sass/submit-btn.sass";

class SubmitBtn extends React.Component {

    constructor(props) {
        super(props);
        this.ref = React.createRef()
    }

    hide = () => this.ref.current.classList.add(style.hidden)

    show = () => this.ref.current.classList.remove(style.hidden)

    onClick = event => {
        this.ref.current.classList.add(style.clicked)
        this.props.onClick(event)
    }

    render() {
        return <button
            ref={this.ref}
            className={
                this.props.className
                    ? `${style.button} ${this.props.className}`
                    : style.button
            }
            onClick={this.onClick}
            type="submit"
        >
            Вперёд!
        </button>
    }
}

export default SubmitBtn;