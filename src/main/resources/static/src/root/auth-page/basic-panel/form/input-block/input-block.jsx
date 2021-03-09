import React from "react";
import style from "./sass/input-block.sass";

class InputBlock extends React.Component {

    constructor(props) {
        super(props);
        this.nodes = {}
        this.ref = React.createRef()
        this.nodes.input = React.createRef()
        this.nodes.label = React.createRef()
    }

    onChange = event => {
        if (this.nodes.input.current.value !== "") {
            this.nodes.label.current.classList.add(style.hidden)
        } else {
            this.nodes.label.current.classList.remove(style.hidden)
        }
        this.props.onChange(event)
    }

    erase = () => this.nodes.input.current.value = ""

    render() {
        return <div
            className={
                this.props.className
                    ? `${style.input_block} ${this.props.className}`
                    : style.input_block
            }
        >
            <label
                ref={this.nodes.label}
                htmlFor={this.props.name}
                className={style.label}
            >
                {this.props.label}
            </label>
            <input
                ref={this.nodes.input}
                name={this.props.name}
                type={this.props.secret
                    ? "password"
                    : "text"
                }
                autoComplete={this.props.autoComplete}
                className={style.input}
                onChange={this.onChange}
            />
        </div>
    }
}

export default InputBlock;