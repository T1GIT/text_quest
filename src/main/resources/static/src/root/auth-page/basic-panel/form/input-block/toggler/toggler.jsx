import React from "react";
import style from "./sass/toggler.sass";
import Component from "../../../../../../util/component";
import Svg from "../../../../../../util/svg";
import eye_svg from "./svg/eye.svg";
import key_svg from "./svg/key.svg";

class Toggler extends Component {

    constructor(props) {
        super(props);
        this.nodes.svg_eye = React.createRef()
        this.nodes.svg_key = React.createRef()
        this.action = "show"
    }

    afterRender() {
        this.nodes.svg_eye.addClass(style.hidden)
    }

    onClick = event => {
        this.props.onClick(this.action)
        switch (this.action) {
            case "show":
                this.nodes.svg_eye.removeClass(style.hidden)
                this.nodes.svg_key.addClass(style.hidden)
                this.action = "hide"
                break
            case "hide":
                this.nodes.svg_eye.addClass(style.hidden)
                this.nodes.svg_key.removeClass(style.hidden)
                this.action = "show"
                break
        }
    }

    render() {
        return <div
            ref={this.self}
            className={style.toggler}
            onClick={this.onClick}
        >
            <Svg ref={this.nodes.svg_eye} className={style.svg + " " + style.hidden} svg={eye_svg}/>
            <Svg ref={this.nodes.svg_key} className={style.svg} svg={key_svg}/>
        </div>
    }
}

export default Toggler;