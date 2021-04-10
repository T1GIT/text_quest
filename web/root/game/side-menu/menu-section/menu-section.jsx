import React from "react";
import Component from "../../../../util/component";
import style from "./sass/menu-section.sass"

class MenuSection extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return <div
            ref={this.self}
            className={style.menu_section}
            onClick={this.props.onClick}>
            {this.props.text}
        </div>
    }
}

export default MenuSection;