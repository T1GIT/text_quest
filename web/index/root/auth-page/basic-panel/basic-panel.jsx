import React from "react";
import style from "./basic-panel.sass";
import Header from "./header/header";
import Form from "./form/form";
import Component from "../../../../util/component";

class BasicPanel extends Component {

    constructor(props) {
        super(props);
        this.nodes.form = React.createRef()
        this.nodes.header = React.createRef()
        this.page = "log"
    }

    reset = () => {
        this.nodes.form.reset()
        this.nodes.header.reset()
        super.reset()
    }

    onChangePage = pageName => {
        this.nodes.form.changePage(pageName)
    }

    render() {
        return <div
            ref={this.self}
            className={style.basic}
        >
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