import React from "react";
import style from "./root.sass";
import Background from "./background/background";
import AuthPage from "./auth-page/auth-page";
import Game from "./game/game";

class Root extends React.Component {

    constructor(props) {
        super(props);
        this.ref = React.createRef()
        this.nodes = {}
        this.nodes.game = React.createRef()
        this.nodes.auth_page = React.createRef()
    }

    componentDidMount() {
        setTimeout(this.load, 0)
        window.login = this.login
        window.logout = this.logout
    }

    load = () => {
        this.ref.current.classList.add(style.loaded)
    }

    login = name => {
        this.nodes.game.current.setName(name)
        // TODO
        this.nodes.auth_page.current.hide()
        console.log("login")
    }

    logout = () => {
        this.nodes.auth_page.current.show()
        console.log("logout")
        // TODO
    }

    render() {
        return <div
            ref={this.ref}
            className={style.root}
        >
            <Background/>
            <AuthPage ref={this.nodes.auth_page}/>
            <Game ref={this.nodes.game}/>
        </div>;
    }
}

export default Root;