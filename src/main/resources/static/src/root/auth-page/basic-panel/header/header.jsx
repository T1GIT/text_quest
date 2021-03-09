import React from "react";
import style from "./sass/header.sass";

import HeaderBtn from "./header-btn/header-btn";

class Header extends React.Component {

    constructor(props) {
        super(props);
        this.nodes = {}
        this.nodes.log_btn = React.createRef()
        this.nodes.reg_btn = React.createRef()
    }

    onChangePage = pageName => event => {
        switch (pageName) {
            case "log":
                this.nodes.reg_btn.current.unSelect()
                this.nodes.log_btn.current.select()
                break
            case "reg":
                this.nodes.log_btn.current.unSelect()
                this.nodes.reg_btn.current.select()
                break
        }
        this.props.onChangePage(pageName)
    }

    render() {
        return <div className={style.header}>
            <HeaderBtn
                ref={this.nodes.log_btn}
                defaultSelect={true}
                text="Вход"
                onClick={this.onChangePage("log")}
            />
            <HeaderBtn
                ref={this.nodes.reg_btn}
                text="Регистрация"
                onClick={this.onChangePage("reg")}
            />
        </div>;
    }
}

export default Header;