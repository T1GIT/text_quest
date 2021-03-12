import React from "react";
import style from "./sass/auth-page.sass";
import OauthPanel from "./oauth-panel/oauth-panel";
import BasicPanel from "./basic-panel/basic-panel";
import Component from "../../util/component";

class AuthPage extends Component {

    constructor(props) {
        super(props);
        this.nodes.basicPanel = React.createRef()
        this.nodes.oauthPanel = React.createRef()
    }

    afterRender() {
        if (window.isAuthorised) {
            this.hide()
        }
    }

    show = () => {
        this.self.classList.remove(style.hidden)
        // TODO
    }

    hide = () => {
        this.nodes.basicPanel.reset()
        this.nodes.oauthPanel.reset()
        this.self.classList.add(style.hidden)
        // TODO
    }

    reset() {
        this.show()
        super.reset();
    }

    render() {
        return <div className={style.wrap} ref={this.self}>
            <div className={style.auth_block}>
                <BasicPanel ref={this.nodes.basicPanel}/>
                <OauthPanel ref={this.nodes.oauthPanel}/>
            </div>
        </div>
    }
}

export default AuthPage;