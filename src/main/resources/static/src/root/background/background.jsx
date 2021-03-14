import React from "react";
import Style from "./sass/background.sass";
import Component from "../../util/component";

class Background extends Component {
    render() {
        return <div
            ref={this.self}
            className={Style.background}>

        </div>;
    }
}

export default Background;