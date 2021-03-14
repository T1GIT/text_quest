import React from "react";

class Component extends React.Component {

    constructor(props) {
        super(props);
        this.self = React.createRef()
        this.nodes = {}
    }

    afterRender() {
    }

    componentDidMount() {
        this.self = this.self.current
        for (let node in this.nodes) {
            this.nodes[node] = this.nodes[node].current
        }
        this.afterRender()
    }

    reset() {
        for (let node in this.nodes) {
            this.nodes[node].reset()
        }
    }
}

export default Component;