import React, {Component} from "react";
import styles from "./app.sass";

class MyComponent extends Component {
    onClick = (event) => {
        console.log(500)
    }

    render() {
        return <div
            className={styles.intro}
            onClick={this.onClick}
        >
            Hello World
        </div>;
    }
}

export default MyComponent;