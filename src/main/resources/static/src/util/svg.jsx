import React from "react";
import Component from "./component";

class Svg extends Component {
    render() {
        return <svg
            ref={this.self}
            onClick={this.props.click}
            className={this.props.className}>
            <use xlinkHref={this.props.svg}/>
        </svg>
    }
}

export default Svg;