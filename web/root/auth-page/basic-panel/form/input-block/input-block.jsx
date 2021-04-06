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
        this.height = $(this.self).css("height")
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
        this.props.onChange()
    }

    isValid = () => this.valid

    hide = () => {
        let el = $(this.self)
        el.addClass(style.hidden)
        el.css({height: "0px"})
    }

    show = () => {
        let el = $(this.self)
        el.css({height: this.height})
        el.removeClass(style.hidden)
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