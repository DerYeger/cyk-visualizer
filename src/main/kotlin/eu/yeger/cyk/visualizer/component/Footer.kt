package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.visualizer.cssClasses
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import styled.css
import styled.styledA
import styled.styledDiv
import styled.styledImg

val footerComponent = functionalComponent<RProps> {
    styledDiv {
        cssClasses("text-center", "bg-dark", "py-3")
        css {
            position = Position.absolute
            bottom = 0.px
            width = 100.pct
            height = 64.px
            verticalAlign = VerticalAlign.middle
        }
        styledA(href = "https://github.com/DerYeger/cyk-visualizer") {
            cssClasses("text-white")
            css {
                textDecoration = TextDecoration.none
                hover {
                    textDecoration = TextDecoration.none
                }
            }
            styledImg(alt = "GitHub", src = "GitHub-Mark.png") {
                cssClasses("mr-3")
            }
            +"github.com/DerYeger/cyk-visualizer"
        }
    }
}

fun RBuilder.footer() {
    child(footerComponent) { }
}
