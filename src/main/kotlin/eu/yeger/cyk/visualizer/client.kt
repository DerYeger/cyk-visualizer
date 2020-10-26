package eu.yeger.cyk.visualizer

import eu.yeger.cyk.visualizer.component.app
import eu.yeger.cyk.visualizer.component.footer
import kotlinx.browser.document
import kotlinx.browser.window
import react.dom.render

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            app()
        }
        render(document.getElementById("footer")) {
            footer()
        }
    }
}
