import React from "react";
import style from "./sass/submit-btn.sass";
import Component from "../../../../../util/component";

class SubmitBtn extends Component {

    hide = () => $(this.self).addClass(style.hidden)

    show = () => $(this.self).removeClass(style.hidden)

    submit = () => {
        return $(this.self).addClass(style.clicked);
    }

    reset() {
        $(this.self).removeClass(style.clicked)
        super.reset();
    }

    render() {
        return <button
            ref={this.self}
            className={style.button}
            onClick={this.props.submit}
            type="submit"
        >
            Вперёд!
        </button>
    }
}

export default SubmitBtn;