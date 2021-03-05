import React, {Component} from "react";
import Style from "./oauth-button.sass";

class OauthButton extends Component {

    onClick = href => event => {
        location.href = href;
    }

    render() {
        return <button className={`${Style.button} ${this.props.className}`}
                       onClick={this.onClick(btnHref[this.props.hrefName])}>
            {this.props.text}
        </button>
    }
}

export default OauthButton;