import React, {Component} from "react";
import styles from "./app.sass";

class MyComponent extends Component {
    render() {
        return <div className={styles.intro}>Hello World</div>;
    }
}

export default MyComponent;