import React from "react";
import Component from "../../util/component";
import Background from "../background/background";
import style from "./sass/game.sass"
import SideMenu from "./side-menu/side-menu";

class Game extends Component {

    constructor(props) {
        super(props);
        this.nodes.game = React.createRef()
    }

    show = () => {
        this.removeClass(style.hidden)
    }

    hide = () => {
        $(this.self).addClass(style.hidden)
        this.reset()
    }

    reset() {
        // TODO
        super.reset();
    }

    render() {
        return <div ref={this.self} className={style.game}>
            <Background ref={this.nodes.game} left="#44A08D" right="#093637"/>
            <SideMenu/>
        </div>
    }
}

export default Game;