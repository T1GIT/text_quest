import React from "react";
import style from "./sass/input-block.sass";
import Hint from "./hint/hint";
import Label from "./label/label";
import Component from "../../../../../util/component";
import Input from "./input/input";
import Toggler from "./toggler/toggler";

class InputBlock extends Component {

    constructor(props) {
        super(props);
        this.nodes.input = React.createRef()
        this.nodes.label = React.createRef()
        this.nodes.hint = React.createRef()
        this.valid = false
    }

    afterRender() {
        if (this.props.type in ["psw", "rep_psw"])
            this.nodes.svg_eye.addClass(style.hidden)
    }

    onChange = event => {
        let input = this.nodes.input
        let lbl = this.nodes.label
        let hint = this.nodes.hint
        if (input.getValue() === "") {
            lbl.show()
            hint.reset()
            this.valid = false
        } else {
            lbl.hide()
            const res = this.props.validator(input.getValue())
            hint.changeText(res.hint)
            this.valid = res.valid
        }
    }

    isValid = () => this.valid

    hide = () => {
        this.height = this.self.offsetHeight
        this.self.classList.add(style.hidden);
        this.self.style.height = "0px"
    }

    show = () => {
        this.self.style.height = this.height + "px"
        this.self.classList.remove(style.hidden);
    }

    getValue = () => this.nodes.input.getValue();

    onTogglerClick = action => {
        switch (action) {
            case "hide":
                this.nodes.input.setType("password")
                break
            case "show":
                this.nodes.input.setType("text")
                break
        }
    }

    reset = () => {
        this.valid = false
        super.reset()
    }

    render() {
        return <div
            ref={this.self}
            className={style.input_block}
        >
            <Label
                ref={this.nodes.label}
                text={this.props.label}
            />
            <Input
                ref={this.nodes.input}
                onChange={this.onChange}
                type={this.props.type}
                field={this.props.toggler}
            />
            {this.props.toggler && <Toggler onClick={this.onTogglerClick}/>}
            <Hint ref={this.nodes.hint}/>
        </div>
    }
}

export default InputBlock;