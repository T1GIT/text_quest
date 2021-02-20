import React, {Component} from "react";
import styles from "./app.sass";
import Icon from "../../media/svg/icon.svg";

class MyComponent extends Component {
    onClick = (event) => {
        console.log(500)
    }

    render() {
        let name = "documents"
        return <div
            className={styles.intro}
            onClick={this.onClick}
        >
            <Icon/>
            Hello World
        </div>;
    }
}

export default MyComponent;