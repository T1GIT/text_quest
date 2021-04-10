import React from "react";
import style from "./basic-panel.sass";
import Header from "./header/header";
import Form from "./form/form";
import Component from "../../../util/component";

class BasicPanel extends Component {

    def_page = "reg"

    constructor(props) {
        super(props);
        this.nodes.form = React.createRef()
        this.nodes.header = React.createRef()
    }

    afterRender() {
        const {form, header} = this.nodes
        form.changePage(this.def_page)
        header.onChangePage(this.def_page)
    }

    onChangePage = pageName => this.nodes.form.changePage(pageName)

    reset() {
        const {form, header} = this.nodes
        form.changePage(this.def_page)
        header.onChangePage(this.def_page)
        super.reset();
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