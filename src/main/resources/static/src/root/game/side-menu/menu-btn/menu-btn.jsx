import React from "react";
import Component from "../../../../util/component";
import style from "./sass/menu-btn.sass"

class MenuBtn extends Component {

    constructor(props) {
        super(props);
        this.nodes.game = React.createRef()
    }

    setIcon = iconName => {

    }

    reset() {
        // TODO
        super.reset();
    }

    render() {
        return <div ref={this.self} className={style.menu_btn}>
        </div>
    }
}

export default MenuBtn;