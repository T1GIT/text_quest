import React from "react";
import Component from "../../util/component";
import Background from "../background/background";
import style from "./sass/game.sass"
import SideMenu from "./side-menu/side-menu";
import axios  from "axios";
import {Client} from "@stomp/stompjs"

class Game extends Component {
    ROW_FADING_SPEED = 2

    constructor(props) {
        super(props);
        this.nodes.game = React.createRef()
        this.nodes.menu = React.createRef()
        this.client = undefined
        this.lastMessage = undefined
    }

    show = () => super.show(style.hidden)

    hide = () => super.hide(style.hidden)

    start = () => {
        const client = new Client();
        this.client = client
        client.brokerURL = `ws://${window.location.host}/game/connect`
        client.onConnect = this.onConnect
        client.onDisconnect = this.onDisconnect
        client.onStompError = client.onWebSocketError = receipt => console.error(receipt)
        client.activate()
    }

    onDisconnect = ev => {
        console.log(ev)
    }

    onConnect = () => axios
        .get("/game/start")
        .then(response => {
            const {socketId, lastNode} = (response.data)
            console.log(lastNode)
            if ("delay" in lastNode) {
                this.showMessage(lastNode)
            } else {
                this.showQuestion(lastNode)
            }
            this.client.subscribe(`/user/${socketId}/next_node`, this.onMessage);
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

    setNewNode = el => {
        if (this.lastMessage) {
            $(this.lastMessage).animate({opacity: 0}, 1000, undefined, () => {
                this.lastMessage.remove();
                this.lastMessage = el;
            })
        } else {
            this.lastMessage = el
        }
    }

    showMessage = node => {
        const {text} = node
        let items = text.split("\n").reverse()
        let el = document.createElement("div")
        el.classList.add(style.message)
        this.setNewNode(el)
        this.self.append(el)
        let recursRecord = (items, msg) => {
            let row = document.createElement("p")
            row.classList.add(style.hidden, style.row)
            row.innerText = items.pop()
            msg.append(row)
            setTimeout(() => {row.classList.remove(style.hidden)}, 1)
            if (items.length > 0) {
                setTimeout(() => {
                    recursRecord(items, msg)
                }, 100 * row.innerText.length / this.ROW_FADING_SPEED)
            }
        }
        recursRecord(items, el)
    }

    showQuestion = node => {
        const {answers} = node;
        console.log(answers)
        let questionNode = document.createElement("div")
        questionNode.classList.add(style.question)
        this.setNewNode(questionNode)
        this.self.append(questionNode)
        for (let k in answers) {
            let btnNode = document.createElement("p")
            btnNode.classList.add(style.btn)
            btnNode.innerText = answers[k].text
            btnNode.onclick = () => axios.post("/game/answer", answers[k])
            questionNode.append(btnNode)
        }
    }

    render() {
        return <div ref={this.self} className={style.game}>
            <Background ref={this.nodes.game} left="#44A08D" right="#093637"/>
            <SideMenu ref={this.nodes.menu}/>
        </div>
    }
}

export default Game;