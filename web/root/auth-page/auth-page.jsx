import React from "react";
import style from "./auth-page.sass";
import OauthPanel from "./oauth-panel/oauth-panel";
import BasicPanel from "./basic-panel/basic-panel";
import Component from "../../util/component";
import Background from "../background/background";

class AuthPage extends Component {

    constructor(props) {
        super(props);
        this.nodes.basicPanel = React.createRef()
        this.nodes.oauthPanel = React.createRef()
        this.elems.authBlock = React.createRef()
    }

    afterRender() {
        let el = $(this.elems.authBlock)
        el.css({width: el.css("width")})
    }

    show = () => super.show(style.hidden)

    hide = () => {
        this.nodes.basicPanel.reset()
        this.nodes.oauthPanel.reset()
        super.hide(style.hidden)
    }

    render() {
        return <div className={style.wrap} ref={this.self}>
            <Background left="#ff9fe0" right="#a2edff"/>
            <div ref={this.elems.authBlock} className={style.auth_block}>
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