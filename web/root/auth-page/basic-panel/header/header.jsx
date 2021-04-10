import React from "react";
import style from "./sass/header.sass";

import HeaderBtn from "./header-btn/header-btn";
import Component from "../../../../util/component";

class Header extends Component {

    constructor(props) {
        super(props);
        this.nodes.log_btn = React.createRef()
        this.nodes.reg_btn = React.createRef()
    }

    onChangePage = pageName => event => {
        switch (pageName) {
            case "log":
                this.nodes.reg_btn.unSelect()
                this.nodes.log_btn.select()
                break
            case "reg":
                this.nodes.log_btn.unSelect()
                this.nodes.reg_btn.select()
                break
        }
        this.props.onChangePage(pageName)
    }

    reset = () => {
        this.nodes.log_btn.unSelect()
        this.nodes.reg_btn.select()
        super.reset()
    }

    render() {
        return <div
            ref={this.self}
            className={style.header}
        >
            <HeaderBtn
                ref={this.nodes.log_btn}
                text="Вход"
                onClick={this.onChangePage("log")}
            />
            <HeaderBtn
                ref={this.nodes.reg_btn}
                defaultSelect={true}
                text="Регистрация"
                onClick={this.onChangePage("reg")}
            />
        </div>;
    }
}

export default Header;