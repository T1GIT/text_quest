import React from "react";
import Component from "../../../util/component";
import style from "./sass/side-menu.sass"
import MenuBtn from "./menu-btn/menu-btn";
import MenuSection from "./menu-section/menu-section";
import axios from "axios";

class SideMenu extends Component {

    constructor(props) {
        super(props);
    }

    show = () => {
        $(this.self).removeClass(style.hidden)
    }

    hide = () => {
        $(this.self).addClass(style.hidden)
    }

    onClickLogout = event => {
        window.logout()
        axios.post("/auth/logout"
            ).then(response => {
                if (response.data.accepted) {

                } else {
                    alert(response.data.msg)
                }
            }).catch(error => {
                console.error(error)
            })
    }

    changeState = state => {
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
            <MenuBtn onClick={this.changeState}/>
            <div className={style.side_menu}>
                <MenuSection
                    text={"Настройки"}
                />
                <MenuSection
                    text={"Выйти"}
                    onClick={this.onClickLogout}
                />
            </div>
        </div>
    }
}

export default SideMenu;