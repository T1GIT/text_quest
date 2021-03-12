import React from "react";
import style from "./form.sass";
import {validateEmail, validatePsw} from "./js/validation";


import InputBlock from "./input-block/input-block";
import SubmitBtn from "./submit-btn/submit-btn";

class Form extends React.Component {

    constructor(props) {
        super(props);
        this.nodes = {}
        this.nodes.email_in = React.createRef()
        this.nodes.psw_in = React.createRef()
        this.nodes.re_psw_in = React.createRef()
        this.nodes.btn = React.createRef()
    }

    componentDidMount() {
        this.nodes.re_psw_in.current.hide()
    }

    onGoClick = event => {

    }

    validateRepPsw = rep_psw => this.nodes.psw_in.current.getValue() === rep_psw

    changePage = pageName => {
        this.nodes.email_in.current.erase()
        this.nodes.psw_in.current.erase()
        this.nodes.re_psw_in.current.erase()
        switch (pageName) {
            case "log":
                this.nodes.re_psw_in.current.hide()
                break
            case "reg":
                this.nodes.re_psw_in.current.show()
                break
        }
    }

    reset = () => {
        this.nodes.email_in.current.erase()
        this.nodes.psw_in.current.erase()
        this.nodes.re_psw_in.current.erase()
        this.nodes.re_psw_in.current.hide()
    }

    render() {
        return <form
            className={style.form}
            ref={this.ref}
        >
            <InputBlock
                ref={this.nodes.email_in}
                label="Почта"
                type="mail"
                validator={validateEmail}
            />
            <InputBlock
                ref={this.nodes.psw_in}
                label="Пароль"
                type="psw"
                validator={validatePsw}
            />
            <InputBlock
                ref={this.nodes.re_psw_in}
                label="Повторите пароль"
                type="rep_psw"
                validator={this.validateRepPsw}
            />
            <SubmitBtn
                ref={this.nodes.btn}
                onClick={this.onGoClick}
            />
        </form>;
    }
}

export default Form;