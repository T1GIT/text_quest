import React from "react";
import style from "./sass/input-block.sass";
import Hint from "./hint/hint";
import Label from "./label/label";

class InputBlock extends React.Component {

    constructor(props) {
        super(props);
        this.nodes = {}
        this.ref = React.createRef()
        this.nodes.input = React.createRef()
        this.nodes.label = React.createRef()
        this.nodes.hint = React.createRef()
        this.valid = false
    }

    onChange = event => {
        const input = this.nodes.input.current
        const lbl = this.nodes.label.current
        const hint = this.nodes.hint.current
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

    hide = () => this.ref.current.classList.add(style.hidden)

    show = () => this.ref.current.classList.remove(style.hidden)

    getValue = () => this.nodes.input.current.value

    erase = () => {
        this.nodes.input.current.value = ""
        this.nodes.label.current.show()
        this.nodes.hint.current.changeText("")
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
            ref={this.ref}
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