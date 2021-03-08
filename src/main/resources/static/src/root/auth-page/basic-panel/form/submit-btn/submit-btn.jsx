import React from "react";
import style from "./submit-btn.sass";

class SubmitBtn extends React.Component {

    constructor(props) {
        super(props);
        this.ref = React.createRef()
    }

    onClick = event => {
        this.ref.current.classList.add(style.clicked)
        this.props.onClick(event)
    }

    render() {
        return <span
            ref={this.ref}
            className={style.button}
            onClick={this.onClick}
        >
            Вперёд!
        </span>
    }
}

export default SubmitBtn;