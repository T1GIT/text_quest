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

    hide(hiddenClassName, callback) {
        let el = $(this.self)
        let dur = parseFloat(el.css("transition-duration").slice(0, -1)) * 1000
        el.addClass(hiddenClassName)
        callback.bind(el)
        setTimeout(() => {
            el.css({display: "none"})
            callback()
        }, dur)
    }

    show(hiddenClassName, callback) {
        let el = $(this.self)
        el.css({display: ""})
        callback.bind(el)
        setTimeout(() => {
            el.removeClass(hiddenClassName)
            callback()
        }, 1)
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