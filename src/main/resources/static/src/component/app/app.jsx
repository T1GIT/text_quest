import React, {Component} from "react";
import styles from "./app.sass";

class MyComponent extends Component {
    onClick = (event) => {
        console.log(500)
    }

    render() {
        return <div
            className={styles.intro}>Hello World
            onClick={this.onClick}
        </div>;
    }
}

export default MyComponent;