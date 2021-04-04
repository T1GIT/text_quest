import React from "react";
import style from "./sass/label.sass";
import Component from "../../../../../../../util/component";

class Label extends Component {

    constructor(props) {
        super(props);
        this.ref = React.createRef()
    }

    hide = () => {
        this.self.classList.add(style.hidden);
    }

    show = () => {
        this.self.classList.remove(style.hidden);
    }

    reset() {
        this.show();
        super.reset();
    }

    render() {
        return <div
            ref={this.self}
            className={style.label}
        >
            {this.props.text}
        </div>
    }
}

export default Label;