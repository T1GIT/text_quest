import React from "react";
import style from "./sass/hint.sass";

class Hint extends React.Component {

    constructor(props) {
        super(props);
        this.ref = React.createRef()
        this.hint_timer = null
    }

    changeText = (res) => {
        const PERIOD = 500
        const hint = this.ref.current
        this.hide()
        let func;
        if (res) {
            func = () => {
                hint.innerText = res
                this.show()
            }
        } else {
            func = () => hint.innerText = ""
        }
        if (this.hint_timer) clearTimeout(this.hint_timer)
        this.hint_timer = setTimeout(func, PERIOD)
    }

    hide = () => this.ref.current.classList.add(style.hidden)

    show = () => this.ref.current.classList.remove(style.hidden)

    render() {
        return <div
            ref={this.ref}
            className={style.hint}
        >
        </div>
    }
}

export default Hint;