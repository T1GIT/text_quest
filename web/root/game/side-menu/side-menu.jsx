import React from "react";
import Component from "../../../util/component";
import style from "./sass/side-menu.sass"
import MenuBtn from "./menu-btn/menu-btn";
import MenuSection from "./menu-section/menu-section";
import axios from "axios";

class SideMenu extends Component {

    constructor(props) {
        super(props);
        this.nodes.toggler = React.createRef()
        this.elems.menu = React.createRef()
        this.opened = false
    }

    afterRender() {
        this.nodes.toggler.setIcon("menu")
        $(this.elems.menu).addClass(style.hidden)
    }

    hide = () => super.hide(style.hidden)

    show = () => super.show(style.hidden)


    onClickLogout = event => {
        window.logout()
        axios.post("/auth/logout"
            ).then(response => {
            const {accepted, msg} = response.data;
            if (accepted) {

                } else {
                    alert(msg)
                }
            }).catch(error => {
                console.error(error)
            })
    }

    onClickBtn = event => {
        const {toggler} = this.nodes
        const {menu} = this.elems
        if (this.opened) {
            toggler.setIcon("menu")
            $(menu).addClass(style.hidden)
            this.opened = false
        } else {
            toggler.setIcon("row")
            $(menu).removeClass(style.hidden)
            this.opened = true
        }
    }

    reset() {
        $(this.elems.menu).addClass(style.hidden)
        this.nodes.toggler.setIcon("menu")
        this.opened = false
        super.reset();
    }

    render() {
        return <div ref={this.self} className={style.wrap}>
            <MenuBtn
                ref={this.nodes.toggler}
                onClick={this.onClickBtn}
            />
            <div
                ref={this.elems.menu}
                className={style.side_menu}>
                <MenuSection
                    text={"Профиль"}
                />
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