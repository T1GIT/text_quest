import React from "react";
import style from "./sass/submit-btn.sass";
import Component from "../../../../../util/component";

class SubmitBtn extends Component {

    hide = () => this.self.classList.add(style.hidden)

    show = () => this.self.classList.remove(style.hidden)

    render() {
        return <button
            ref={this.self}
            className={style.button}
            type="submit"
        >
            Вперёд!
        </button>
    }
}

export default SubmitBtn;