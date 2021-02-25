import React, {Component} from "react";
import Style from "./sass/root.sass";

class Root extends Component {
    VkOnClick = () => {
        location.href = "http://oauth.vk.com/authorize?client_id=7771906&redirect_uri=localhost:8080/oauth/vk/code&response_type=code&display=popup"
    }

    YandexOnClick = () => {
        location.href = "https://oauth.yandex.ru/authorize?client_id=fe4effc1e75340d8a1a93f5acfb09827&redirect_uri=localhost:8080/oauth/yandex/code&response_type=token&display=popup"
    }

    render() {
        return <div className={Style.background}>
            <button onClick={this.VkOnClick}>
                VK
            </button>
            <button onClick={this.YandexOnClick}>
                YANDEX
            </button>
        </div>;
    }
}

export default Root;