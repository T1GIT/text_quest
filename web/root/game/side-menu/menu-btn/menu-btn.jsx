import React from "react";
import Component from "../../../../util/component";
import style from "./sass/menu-btn.sass"

class MenuBtn extends Component {

    setIcon = iconName => {
        switch (iconName) {
            case "row":
                $(this.self).addClass(style.row)
                break
            case "menu":
                $(this.self).removeClass(style.row)
                break
        }
    }

    render() {
        return <div
            ref={this.self}
            className={style.menu_btn}
            onClick={this.props.onClick}>
            <div></div>
            <div></div>
            <div></div>
        </div>
    }
}

export default MenuBtn;