import React from "react";
import style from "./sass/label.sass";
import Component from "../../../../../../util/component";

class Label extends Component {

    constructor(props) {
        super(props);
        this.ref = React.createRef()
    }

    hide = () => super.hide(style.hidden)

    show = () => super.show(style.hidden)

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