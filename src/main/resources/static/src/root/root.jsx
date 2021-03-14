import React from "react";
import style from "./root.sass";
import Background from "./background/background";
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
        setTimeout(this.load, 10)
        window.login = this.login
        window.logout = this.logout

        alert("TEST BUILD") // PROD: Remove
    }

    load = () => {
        this.self.classList.add(style.loaded)
    }

    login = () => {
        // TODO
        this.nodes.auth_page.hide()
        console.log("login")
    }

    logout = () => {
        this.nodes.auth_page.show()
        console.log("logout")
        // TODO
    }

    render() {
        return <div
            ref={this.self}
            className={style.root}
        >
            <Background/>
            <AuthPage ref={this.nodes.auth_page}/>
            <Game ref={this.nodes.game}/>
        </div>;
    }
}

export default Root;