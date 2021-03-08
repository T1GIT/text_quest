import React from "react";
import style from "./header.sass";
import HeaderBtn from "./header-btn/header-btn";

class Header extends React.Component {

    onLogClick = event => {

    }

    onRegClick = event => {

    }

    render() {
        return <div className={style.header}>
            <HeaderBtn
                defaultSelect="True"
                text="Вход"
                onClick={this.onLogClick}
            />
            <HeaderBtn
                text="Регистрация"
                onClick={this.onRegClick}
            />
        </div>;
    }
}

export default Header;