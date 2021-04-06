import React from "react";

class Component extends React.Component {

    constructor(props) {
        super(props);
        this.self = React.createRef()
        this.nodes = {}
        this.elems = {}
    }

    addClass(className) {
        this.self.classList.add(className)
    }

    removeClass(className) {
        this.self.classList.remove(className)
    }

    afterRender() {
    }

    componentDidMount() {
        this.self = this.self.current
        for (let node in this.nodes) this.nodes[node] = this.nodes[node].current
        for (let elem in this.elems) this.elems[elem] = this.elems[elem].current
        this.afterRender()
    }

    reset() {
        for (let node in this.nodes) {
            this.nodes[node].reset()
        }
    }
}

export default Component;