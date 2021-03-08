import React from "react";
import style from "./sass/basic-panel.sass";
import Header from "./header/header";
import Form from "./form/form";

class BasicPanel extends React.Component {

    validateEmail = event => {

    }

    validatePsw = event => {

    }

    render() {
        return <div className={style.basic}>
            <Header/>
            <div className={style.form_wrap}>
                <Form rep_psw="False"/>
                <Form rep_psw="True"/>
            </div>
        </div>;
    }
}

export default BasicPanel;