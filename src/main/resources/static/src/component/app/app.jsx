import React, {Component} from "react";
import styles from "./app.sass";
import Icon from "../../media/svg/icon.svg";
import Icon2 from "../../media/svg/icon2.svg";

class MyComponent extends Component {
    onClick = (event) => {
        console.log(500)
    }

    render() {
        return <div
            className={styles.intro}
            onClick={this.onClick}
        >
            <svg>
                <use xlinkHref={Icon}/>
            </svg>
            <svg>
                <use xlinkHref={Icon2}/>
            </svg>
            <svg>
                <use xlinkHref={Icon2}/>
            </svg>
            Hello World
        </div>;
    }
}

export default MyComponent;