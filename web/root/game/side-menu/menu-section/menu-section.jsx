import React from "react";
import Component from "../../../../util/component";
import style from "./sass/menu-section.sass"

class MenuSection extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return <div ref={this.self} className={style.game}>
        </div>
    }
}

export default MenuSection;