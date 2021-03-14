import React from "react";
import style from "./sass/input-block.sass";
import Hint from "./hint/hint";
import Label from "./label/label";
import Component from "../../../../../util/component";

class InputBlock extends Component {

    constructor(props) {
        super(props);
        this.nodes.input = React.createRef()
        this.nodes.label = React.createRef()
        this.nodes.hint = React.createRef()
        this.valid = false
    }

    onChange = event => {
        let input = this.nodes.input
        let lbl = this.nodes.label
        let hint = this.nodes.hint
        if (input.value === "") {
            lbl.show()
            hint.changeText("")
            this.valid = false
        } else {
            lbl.hide()
            const res = this.props.validator(input.value)
            hint.changeText(res.hint)
            this.valid = res.valid
        }
    }

    isValid = () => this.valid

    hide = () => {
        this.self.classList.add(style.hidden);
        this.self.style.height = null
    }

    show = () => {
        this.self.style.height = this.nodes.input.offsetHeight + "px"
        this.self.classList.remove(style.hidden);
    }

    getValue = () => this.nodes.input.value

    reset = () => {
        this.nodes.input.value = ""
        this.nodes.label.show()
        this.nodes.hint.changeText("")
        this.valid = false
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
        return <div
            ref={this.self}
            className={style.input_block}
        >
            <Label
                ref={this.nodes.label}
                text={this.props.label}
            />
            <input
                ref={this.nodes.input}
                name={attr.name}
                type={attr.type}
                autoComplete={attr.autoComplete}
                className={style.input}
                onChange={this.onChange}
            />
            <Hint
                ref={this.nodes.hint}
            />
        </div>
    }
}

export default InputBlock;