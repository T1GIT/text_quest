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

    start = () => {
        const client = new Client();
        client.brokerURL = `ws://${window.location.host}/game/connect`
        client.onConnect = this.onConnect(client)
        client.onStompError = client.onWebSocketError = receipt => console.error(receipt)
        client.activate()
    }

    onConnect = (client) => () => axios
        .get("/game/start")
        .then(response => {
            const {socketId, lastNode} = (response.data)
            if ("delay" in lastNode) {
                this.showMessage(lastNode)
            } else {
                this.showQuestion(lastNode)
            }
            client.subscribe(`/user/${socketId}/next_node`, this.onMessage);
        })
        .catch(error => console.error(error))

    onMessage = (msg) => {
        switch (JSON.parse(msg.body)) {
            case "question": axios
                .get("/game/question")
                .then(response => this.showQuestion(response.data))
                .catch(error => console.error(error))
                break
            case "message": axios
                .get("/game/message")
                .then(response => this.showMessage(response.data))
                .catch(error => console.error(error))
                break
            default:
                console.error("Unsupported node type: " + JSON.parse(msg.body))
        }
    }

    showMessage = node => {
        const {text} = node


        console.log("message", text)
    }

    showQuestion = node => {
        const {answers} = node;
        console.log("question", answers)
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