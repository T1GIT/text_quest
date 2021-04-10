import React from "react";
import Component from "../../util/component";
import Background from "../background/background";
import style from "./sass/game.sass"
import SideMenu from "./side-menu/side-menu";

class Game extends Component {

    constructor(props) {
        super(props);
        this.nodes.game = React.createRef()
        this.nodes.menu = React.createRef()
    }

    show = () => super.show(style.hidden)

    hide = () => super.hide(style.hidden)

    reset() {
        super.reset();
    }

    render() {
        return <div ref={this.self} className={style.game}>
            <Background ref={this.nodes.game} left="#44A08D" right="#093637"/>
            <SideMenu ref={this.nodes.menu}/>
        </div>
    }
}

export default Game;