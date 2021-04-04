import React from "react";
import style from "./sass/hint.sass";
import Component from "../../../../../../../util/component";

class Hint extends Component {

    constructor(props) {
        super(props);
        this.hint_timer = null
    }

    changeText = (res) => {
        const PERIOD = 500
        this.hide()
        let func;
        if (res) {
            func = () => {
                this.self.innerText = res
                this.show()
            }
        } else {
            func = () => this.self.innerText = ""
        }
        if (this.hint_timer) clearTimeout(this.hint_timer)
        this.hint_timer = setTimeout(func, PERIOD)
    }

    hide = () => this.self.classList.add(style.hidden)

    show = () => this.self.classList.remove(style.hidden)

    reset() {
        this.changeText("")
        super.reset();
    }

    render() {
        return <div
            ref={this.self}
            className={style.hint}
        >
        </div>
    }
}

export default Hint;