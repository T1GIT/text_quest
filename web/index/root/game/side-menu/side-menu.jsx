import React from "react";
import Component from "../../../../util/component";
import style from "./sass/side-menu.sass"
import MenuBtn from "./menu-btn/menu-btn";

class SideMenu extends Component {

    constructor(props) {
        super(props);
    }

    show = () => {
        this.removeClass(style.hidden)
    }

    hide = () => {
        this.addClass(style.hidden)
    }

    onClickBtn = state => {
        switch (state) {
            case "hide":
                this.hide()
                break
            case "show":
                this.show()
                break
        }
    }

    reset() {
        this.hide();
        super.reset();
    }

    render() {
        return <div ref={this.self} className={style.wrap}>
            <MenuBtn onClick={this.onClickBtn}/>
            <div className={style.side_menu}>

            </div>
        </div>
    }
}

export default SideMenu;