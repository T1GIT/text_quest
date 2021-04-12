import React from "react";
import Component from "../../util/component";
import Background from "../background/background";
import style from "./sass/game.sass"
import SideMenu from "./side-menu/side-menu";
import axios  from "axios";
import {Client} from "@stomp/stompjs"

class Game extends Component {

    constructor(props) {
        super(props);
        this.nodes.game = React.createRef()
        this.nodes.menu = React.createRef()
    }

    show = () => super.show(style.hidden)

    hide = () => super.hide(style.hidden)

    onMessage = (msg) => {
        console.log(msg.body)
        switch (JSON.parse(msg.body)) {
            case "question": axios
                .get("/game/question")
                .then(response => console.log(response.data))
                .catch(error => console.error(error))
                break
            case "message": axios
                .get("/game/message")
                .then(response => console.log(response.data))
                .catch(error => console.error(error))
                break
            default:
                console.error("Unsupported node type: " + JSON.parse(msg.body))
        }
    }

    onConnect = (client) => () => axios
        .get("/game/start")
        .then(response => client.subscribe(`/user/${response.data}/next_node`, this.onMessage))
        .catch(error => console.error(error))

    start = () => {
        const client = new Client();
        client.brokerURL = `ws://${window.location.host}/game/connect`
        client.onConnect = this.onConnect(client)
        client.onStompError = client.onWebSocketError = receipt => console.error(receipt)
        client.activate()
    }

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