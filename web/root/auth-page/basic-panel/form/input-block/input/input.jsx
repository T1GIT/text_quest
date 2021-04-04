import React from "react";
import style from "./sass/input.sass";
import Component from "../../../../../../util/component";

class Input extends Component {

    constructor(props) {
        super(props);
    }

    afterRender() {
        if (this.props.field) {
            super.addClass(style.field)
        }
    }

    getValue = () => this.self.value

    setValue = value => this.self.value = value

    setType = type => this.self.type = type

    reset() {
        this.setValue("")
        super.reset();
    }

    render() {
        const attr = {}
        switch (this.props.type) {
            case "mail":
                attr.name = attr.autoComplete = "mail"
                attr.type = "text"
                break
            case "psw":
                attr.name = attr.type = attr.autoComplete = "password"
                break
            case "rep_psw":
                attr.name = attr.type = attr.autoComplete = "password"
                break
        }
        return <input
            ref={this.self}
            className={style.input}
            name={attr.name}
            type={attr.type}
            autoComplete={attr.autoComplete}
            onChange={this.props.onChange}
        >
        </input>
    }
}

export default Input;