import React from "react";

class Component extends React.Component {

    SHOW_DELAY = 100

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
        this.self.style.opacity = 0
        for (let node in this.nodes) this.nodes[node] = this.nodes[node].current
        for (let elem in this.elems) this.elems[elem] = this.elems[elem].current
        this.afterRender()
        setTimeout(() => {
            this.self.style.opacity = null
        }, this.SHOW_DELAY)
    }

    reset() {
        for (let node in this.nodes) {
            this.nodes[node].reset()
        }
    }
}

export default Component;