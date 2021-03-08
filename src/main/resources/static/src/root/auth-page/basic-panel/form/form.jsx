import React from "react";
import style from "./form.sass";
import InputBlock from "./input-block/input-block";
import SubmitBtn from "./submit-btn/submit-btn";

class Form extends React.Component {

    validateEmail = event => {

    }

    validatePsw = event => {

    }

    validateRepPsw = event => {

    }

    goOnClick = event => {

    }

    render() {
        return <form className={style.form}>
            <InputBlock
                label="Почта"
                name="email"
                onChange={this.validateEmail}
            />
            <InputBlock
                label="Пароль"
                name="password"
                onChange={this.validatePsw}
            />
            {this.props.rep_psw &&
            <InputBlock
                label="Повторите пароль"
                name="rep_password"
                onChange={this.validateRepPsw}
            />
            }
            <SubmitBtn
                onClick={this.goOnClick}
            />
        </form>;
    }
}

export default Form;