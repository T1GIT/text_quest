import React from "react";
import style from "./basic-panel.sass";
import Header from "./header/header";
import Form from "./form/form";

class BasicPanel extends React.Component {

    constructor(props) {
        super(props);
        this.nodes = {}
        this.nodes.form = React.createRef()
        this.state = {
            page: "log"
        }
    }

    onChangePage = pageName => {
        if (this.state.page !== pageName) {
            this.state.page = pageName
            this.nodes.form.current.changePage(pageName)
        }
    }

    render() {
        return <div className={style.basic}>
            <Header
                onChangePage={this.onChangePage}
            />
            <Form
                ref={this.nodes.form}
            />
        </div>;
    }
}

export default BasicPanel;