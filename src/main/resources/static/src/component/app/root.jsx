import React, {Component} from "react";
import Style from "./sass/root.sass";

class Root extends Component {
    render() {
        return <div className={Style.background}></div>;
    }
}

export default Root;