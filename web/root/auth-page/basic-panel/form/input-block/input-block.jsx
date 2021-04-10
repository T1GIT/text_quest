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
        if (this.props.toggler) {
            this.nodes.toggler = React.createRef()
            this.showed = false
        }
    }

    afterRender() {
        this.height = $(this.self).css("height")
        if (this.props.toggler)
            this.nodes.toggler.setIcon("eye")
    }


    refresh = () => {
        let {input, label, hint} = this.nodes
        if (input.getValue() === "") {
            label.show()
            hint.reset()
            this.valid = false
        } else {
            label.hide()
            const res = this.props.validator(input.getValue())
            hint.changeText(res.hint)
            this.valid = res.valid
        }
    }

    onChange = event => {
        this.refresh()
        this.props.onChange(event)
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

    onTogglerClick = event => {
        let {input, toggler} = this.nodes
        if (this.showed) {
            input.hideText()
            toggler.setIcon("eye")
        } else {
            input.showText()
            toggler.setIcon("key")
        }
        this.showed = !this.showed
    }

    reset = () => {
        if (this.props.toggler) {
            const {input, toggler} = this.nodes
            this.showed = false
            input.hideText()
            toggler.setIcon("eye")
        }
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
            {this.props.toggler &&
            <Toggler
                ref={this.nodes.toggler}
                onClick={this.onTogglerClick}/>
            }
            <Hint ref={this.nodes.hint}/>
        </div>
    }
}

export default InputBlock;