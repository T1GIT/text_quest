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

    onSubmit = event => {
        event.preventDefault()
        let {email_in, psw_in, re_psw_in} = this.nodes
        if (email_in.isValid() && psw_in.isValid() && (this.page === "log" || re_psw_in.isValid())) {
            switch (this.page) {
                case "log":
                    this.login();
                    break
                case "reg":
                    this.register();
                    break
            }
        }
    }

    login = () => {
        axios.post("/auth/login", {
            mail: this.nodes.email_in.getValue(),
            psw: this.nodes.psw_in.getValue()
        }).then(response => {
            if (response.data.accepted) {
                window.login()
            } else {
                alert(response.data.msg) // TODO: My own alert
            }
        }).catch(error => {
            console.error(error)
        })
    }

    register = () => {
        axios.post("/auth/register", {
            mail: this.nodes.email_in.getValue(),
            psw: this.nodes.psw_in.getValue()
        }).then(response => {
            if (response.data.accepted) {
                window.login()
            } else {
                alert(response.data.msg) // TODO: My own alert
            }
        }).catch(error => {
            console.error(error)
        })
    }

    validateRepPsw = repPsw => validateRepPsw(this.nodes.psw_in.getValue(), repPsw)

    changePage = pageName => {
        if (this.page !== pageName) {
            let {email_in, psw_in, re_psw_in} = this.nodes
            email_in.reset();
            psw_in.reset();
            re_psw_in.reset()
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

    reset = () => {
        this.nodes.re_psw_in.hide()
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
                validator={validateEmail}
            />
            <InputBlock
                ref={this.nodes.psw_in}
                label="Пароль"
                type="psw"
                onSubmit={this.onSubmit}
                validator={validatePsw}
                toggler={true}
            />
            <InputBlock
                ref={this.nodes.re_psw_in}
                label="Повторите пароль"
                type="rep_psw"
                onSubmit={this.onSubmit}
                validator={this.validateRepPsw}
                toggler={true}
            />
            <SubmitBtn
                ref={this.nodes.btn}
            />
        </form>;
    }
}

export default Form;