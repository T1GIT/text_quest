import React from "react";
import style from "./sass/basic-panel.sass";
import Header from "./header/header";
import Form from "./form/form";

class BasicPanel extends React.Component {

    constructor(props) {
        super(props);
        this.nodes = {}
        this.nodes.reg_form = React.createRef()
        this.nodes.log_form = React.createRef()
    }

    componentDidMount() {
        this.nodes.log_form.current.show()
    }

    onChangePage = pageName => {
        switch (pageName) {
            case "log":
                this.nodes.reg_form.current.hide()
                this.nodes.log_form.current.show()
                break
            case "reg":
                this.nodes.log_form.current.hide()
                this.nodes.reg_form.current.show()
                break
        }
    }

    render() {
        return <div className={style.basic}>
            <Header
                onChangePage={this.onChangePage}
            />
            <div className={style.form_wrap}>
                <Form
                    ref={this.nodes.log_form}
                    default_select={true}
                    rep_psw="False"
                />
                <Form
                    ref={this.nodes.reg_form}
                    rep_psw="True"
                />
            </div>
        </div>;
    }
}

export default BasicPanel;