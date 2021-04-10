import React from "react";
import Style from "./background.sass";
import Component from "../../util/component";

class Background extends Component {

    change = (left, right) => {
        this.self.style.background = `linear-gradient(to right, ${left} 0%, ${right} 100%)`
    }

    render() {
        return <div
            ref={this.self}
            className={Style.background}
            style={{background: `linear-gradient(to right, ${this.props.left} 0%, ${this.props.right} 100%)`}}
        >

        </div>;
    }
}

export default Background;