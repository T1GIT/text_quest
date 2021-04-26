import React from "react";
import axios from "axios";
import style from "./form.sass";
import {validateEmail, validatePsw, validateRepPsw} from "./js/validation";


import InputBlock from "./input-block/input-block";
import SubmitBtn from "./submit-btn/submit-btn";
import Component from "../../../../util/component";

class Form extends Component {

    constructor(props) {
        super(props);
        this.nodes.email_in = React.createRef()
        this.nodes.psw_in = React.createRef()
        this.nodes.re_psw_in = React.createRef()
        this.nodes.btn = React.createRef()
        this.page = "reg"
    }

    afterRender() {
        this.nodes.btn.hide()
        super.afterRender();
    }

    onSubmit = event => {
        event.preventDefault()
        let {email_in, psw_in, re_psw_in, btn} = this.nodes
        if (email_in.isValid() && psw_in.isValid() && (this.page === "log" || re_psw_in.isValid())) {
            btn.submit()
            switch (this.page) {
                case "log":
                    setTimeout(this.login, 100)
                    break
                case "reg":
                    setTimeout(this.register, 100)
                    break
            }
        }
    }

    login = () => axios
        .post("/auth/login", {
            mail: this.nodes.email_in.getValue(),
            psw: this.nodes.psw_in.getValue()
        }).then(response => {
            const {accepted, msg} = response.data;
            if (accepted) {
                window.login()
            } else {
                alert(msg) // TODO: My own alert
            }
        }).catch(error => {
            console.error(error)
        }).then(this.reset)

    register = () => axios
        .post("/auth/register", {
            mail: this.nodes.email_in.getValue(),
            psw: this.nodes.psw_in.getValue()
        }).then(response => {
            const {accepted, msg} = response.data;
            if (accepted) {
                window.login()
            } else {
                alert(msg) // TODO: My own alert
            }
        }).catch(error => {
            console.error(error)
        }).then(this.reset)

    validateRepPsw = repPsw => validateRepPsw(this.nodes.psw_in.getValue(), repPsw)

    changePage = pageName => {
        if (this.page !== pageName) {
            let {email_in, psw_in, re_psw_in, btn} = this.nodes
            email_in.reset();
            psw_in.reset();
            re_psw_in.reset()
            btn.hide()
            switch (pageName) {
                case "log":
                    re_psw_in.hide();
                    break
                case "reg":
                    re_psw_in.show();
                    break
            }
            this.page = pageName
        }
    }

    checkValid = event => {
        const {email_in, psw_in, re_psw_in, btn} = this.nodes
        if (event.target === psw_in.nodes.input.self && this.page === "reg") {
            re_psw_in.refresh()
        }
        switch (this.page) {
            case "reg":
                if (email_in.isValid() && psw_in.isValid() && re_psw_in.isValid()) {
                    btn.show()
                } else {
                    btn.hide()
                }
                break
            case "log":
                if (email_in.isValid() && psw_in.isValid()) {
                    btn.show()
                } else {
                    btn.hide()
                }
                break
        }
    }

    reset = () => {
        this.nodes.btn.hide()
        super.reset()
    }

    render() {
        return <form
            ref={this.self}
            className={style.form}
            onSubmit={this.onSubmit}
        >
            <InputBlock
                ref={this.nodes.email_in}
                label="Почта"
                type="mail"
                onSubmit={this.onSubmit}
                onChange={this.checkValid}
                validator={validateEmail}
            />
            <InputBlock
                ref={this.nodes.psw_in}
                label="Пароль"
                type="psw"
                onSubmit={this.onSubmit}
                onChange={this.checkValid}
                validator={validatePsw}
                toggler
            />
            <InputBlock
                ref={this.nodes.re_psw_in}
                label="Повторите пароль"
                type="rep_psw"
                onSubmit={this.onSubmit}
                onChange={this.checkValid}
                validator={this.validateRepPsw}
                toggler
            />
            <SubmitBtn
                ref={this.nodes.btn}
            />
        </form>;
    }
}

export default Form;