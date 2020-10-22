package eu.yeger.cyk.visualizer

import eu.yeger.cyk.visualizer.component.app
import kotlinx.browser.document
import kotlinx.browser.window
import react.child
import react.dom.render

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            child(app) { }
        }
    }
}
