import React from "react";

class Svg extends React.Component {
    render() {
        return <svg className={this.props.className}>
            <use xlinkHref={this.props.svg}/>
        </svg>
    }
}

export default Svg;