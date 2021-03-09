import React from "react";
import style from "./form.sass";

import InputBlock from "./input-block/input-block";
import SubmitBtn from "./submit-btn/submit-btn";

class Form extends React.Component {

    constructor(props) {
        super(props);
        this.ref = React.createRef()
        this.nodes = {}
        this.nodes.email_in = React.createRef()
        this.nodes.psw_in = React.createRef()
        this.nodes.re_psw_in = React.createRef()
    }

    componentDidMount() {
        if (this.props.defaultSelect) {
            this.ref.current.classList.add(style.select)
        }
    }

    onGoClick = event => {

    }

    validateEmail = event => {

    }

    validatePsw = event => {

    }

    validateRepPsw = event => {

    }

    show = () => {
        setTimeout(() => {
                this.ref.current.classList.add(style.select)
            },
            200)

    }

    hide = () => {
        this.ref.current.classList.remove(style.select)
        // this.nodes.forEach((k, v) => {
        //     v.current.erase()
        // })
    }

    render() {
        return <form
            className={style.form}
            ref={this.ref}
        >
            <InputBlock
                ref={this.nodes.email_in}
                className={style.input_block}
                label="Почта"
                name="email"
                autoComplete="email"
                onChange={this.validateEmail}
            />
            <InputBlock
                ref={this.nodes.psw_in}
                className={style.input_block}
                label="Пароль"
                name="psw"
                autoComplete="password"
                secret={true}
                onChange={this.validatePsw}
            />
            {this.props.rep_psw &&
            <InputBlock
                ref={this.nodes.re_psw_in}
                label="Повторите пароль"
                name="rep_psw"
                secret={true}
                onChange={this.validateRepPsw}
            />
            }
            <SubmitBtn
                className={style.submit_btn}
                onClick={this.onGoClick}
            />
        </form>;
    }
}

export default Form;