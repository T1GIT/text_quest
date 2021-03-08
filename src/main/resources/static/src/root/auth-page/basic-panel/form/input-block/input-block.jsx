import React from "react";
import style from "./sass/input-block.sass";

class InputBlock extends React.Component {

    render() {
        return <div className={style.input_block}>
            <label className={style.label}>
                {this.props.label}
            </label>
            <input
                name={this.props.name}
                className={style.input}
                onChange={this.props.onChange}
            />
        </div>
    }
}

export default InputBlock;