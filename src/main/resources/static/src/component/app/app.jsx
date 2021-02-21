import React, {Component} from "react";
import styles from "./app.sass";

class MyComponent extends Component {
    listeners = {
        onClick: (event) => {
            console.log(500)
            console.log({styles}, styles, styles.intro)
        }
    }

    render() {
        return <div
            className="intro"
            onClick={this.listeners.onClick}
        >
            Hello World
        </div>;
    }
}

export default MyComponent;