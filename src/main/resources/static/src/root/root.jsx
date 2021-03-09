import React from "react";
import style from "./root.sass";
import Background from "./background/background";
import AuthPage from "./auth-page/auth-page";
import Game from "./game/game";

class Root extends React.Component {

    constructor(props) {
        super(props);
        this.nodes = {}
        this.nodes.game = React.createRef()
        this.nodes.auth_page = React.createRef()
    }

    login = name => {
        this.nodes.game.setName(name)
        // TODO
        this.nodes.auth_page.hide()
    }

    logout = () => {
        this.nodes.auth_page.show()
        // TODO
    }

    render() {
        return <div className={style.root}>
            <Background/>
            <AuthPage ref={this.nodes.auth_page}/>
            <Game ref={this.nodes.game}/>
        </div>;
    }
}

export default Root;