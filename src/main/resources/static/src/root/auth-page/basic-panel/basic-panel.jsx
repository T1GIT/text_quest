import React from "react";
import style from "./basic-panel.sass";
import Header from "./header/header";
import Form from "./form/form";

class BasicPanel extends React.Component {

    constructor(props) {
        super(props);
        this.nodes = {}
        this.nodes.form = React.createRef()
        this.nodes.header = React.createRef()
        this.page = "log"
    }

    reset = () => {
        this.nodes.form.current.reset()
        this.nodes.header.current.reset()
        this.page = "log"
    }

    onChangePage = pageName => {
        if (this.page !== pageName) {
            this.page = pageName
            this.nodes.form.current.changePage(pageName)
        }
    }

    render() {
        return <div className={style.basic}>
            <Header
                ref={this.nodes.header}
                onChangePage={this.onChangePage}
            />
            <Form
                ref={this.nodes.form}
            />
        </div>;
    }
}

export default BasicPanel;