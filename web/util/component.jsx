import React from "react";

class Component extends React.Component {

    constructor(props) {
        super(props);
        this.self = React.createRef()
        this.nodes = {}
        this.elems = {}
        this.hidden = false
        this.hideTimer = setTimeout(() => {}, 0)
    }

    hide(hiddenClassName, callback = () => {}) {
        if (!this.hidden) {
            clearTimeout(this.hideTimer)
            let el = $(this.self)
            let dur = parseFloat(el.css("transition-duration").slice(0, -1)) * 1000
            el.addClass(hiddenClassName)
            this.hideTimer = setTimeout(() => {
                el.css({display: "none"})
                callback.bind(el)()
            }, dur)
            this.hidden = true
        }
    }

    show(hiddenClassName, callback = () => {}) {
        if (this.hidden) {
            clearTimeout(this.hideTimer)
            let el = $(this.self)
            el.css({display: ""})
            this.hideTimer = setTimeout(() => {
                el.removeClass(hiddenClassName)
                callback.bind(el)()
            }, 1)
            this.hidden = false
        }
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