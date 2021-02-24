import React, {Component} from "react";
import Style from "./sass/root.sass";

class Root extends Component {
    onClick = (event) => {
        $.post(
            "/registration/test",
            {
                key1: 1,
                key2: 2
            },
            (data) => {
                console.log(data)

            }
        )
    }

    render() {
        return <div className={Style.background}>
            <button onClick={this.onClick}>
                BUTTON
            </button>
        </div>;
    }
}

export default Root;