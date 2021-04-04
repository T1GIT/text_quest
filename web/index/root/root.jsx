import React from "react";
import style from "./root.sass";
import AuthPage from "./auth-page/auth-page";
import Game from "./game/game";
import Component from "../../util/component";

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
        this.addClass(style.loaded)
        if (window.error !== "null") {
            alert(window.error)
        }
    }

    login = () => {
        // TODO
        this.nodes.auth_page.hide()
        this.nodes.game.show()
    }

    logout = () => {
        this.nodes.auth_page.show()
        this.nodes.game.hide()
        // TODO
    }

    render() {
        return <div
            ref={this.self}
            className={style.root}
        >
            <AuthPage
                ref={this.nodes.auth_page}
                quote="Самая мощная видеокарта - наше воображение"
            />
            <Game ref={this.nodes.game}/>
        </div>;
    }
}

export default Root;