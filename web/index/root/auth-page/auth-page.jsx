import React from "react";
import style from "./sass/auth-page.sass";
import OauthPanel from "./oauth-panel/oauth-panel";
import BasicPanel from "./basic-panel/basic-panel";
import Component from "../../../util/component";
import Background from "../background/background";

class AuthPage extends Component {

    constructor(props) {
        super(props);
        this.nodes.basicPanel = React.createRef()
        this.nodes.oauthPanel = React.createRef()
    }

    show = () => {
        this.removeClass(style.hidden)
    }

    hide = () => {
        this.nodes.basicPanel.reset()
        this.nodes.oauthPanel.reset()
        this.addClass(style.hidden)
    }

    reset() {
        this.show()
        super.reset();
    }

    render() {
        return <div className={style.wrap} ref={this.self}>
            <Background left="#ff9fe0" right="#a2edff"/>
            <div className={style.auth_block}>
                <BasicPanel ref={this.nodes.basicPanel}/>
                <OauthPanel ref={this.nodes.oauthPanel}/>
            </div>
            <div className={style.quote}>
                {this.props.quote}
            </div>
        </div>
    }
}

export default AuthPage;