import React from "react";
import style from "./root.sass";
import AuthPage from "./auth-page/auth-page";
import Game from "./game/game";
import Component from "../util/component";

class Root extends Component {

    constructor(props) {
        super(props);
        this.nodes.game = React.createRef()
        this.nodes.auth_page = React.createRef()
    }

    afterRender() {
        window.login = this.login
        window.logout = this.logout
        if (window.isAuthorised) {
            this.nodes.auth_page.hide()
        } else {
            this.nodes.game.hide()
        }
        if (window.error !== "null") {
            alert(window.error)
        }
        setTimeout(this.load, 100)
    }

    load = () => {
        $(this.self).addClass(style.loaded)
        let preloader = $("#initialPreloader")
        preloader.animate({opacity: 0}, 100, undefined, () => {preloader.css({display: "none"})})
    }

    login = () => {
        const {auth_page, game} = this.nodes;
        game.show()
        auth_page.hide()
        auth_page.reset()
        window.isAuthorised = true
    }

    logout = () => {
        const {auth_page, game} = this.nodes;
        auth_page.show()
        game.hide()
        game.reset()
        window.isAuthorised = false
    }

    render() {
        return <div
            ref={this.self}
            className={style.root}
        >
            <AuthPage
                ref={this.nodes.auth_page}
                quote="Самая мощная видеокарта - ваше воображение"
            />
            <Game ref={this.nodes.game}/>
        </div>;
    }
}

export default Root;