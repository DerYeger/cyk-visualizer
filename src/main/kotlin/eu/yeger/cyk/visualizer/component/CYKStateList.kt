package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.model.CYKState
import eu.yeger.cyk.visualizer.cssClasses
import kotlinx.html.js.onClickFunction
import react.*
import styled.styledButton
import styled.styledDiv

external interface CYKStateListProps : RProps {
    var cykStates: List<CYKState>
}

val cykStateList = functionalComponent<CYKStateListProps> { cykProps ->
    val (index, setIndex) = useState(cykProps.cykStates.lastIndex)
    if (cykProps.cykStates.isEmpty()) return@functionalComponent
    styledDiv {
        cssClasses("row", "justify-content-md-center")
        styledButton {
            cssClasses("btn", "btn-primary", "align-self-center", "col-1")
            +"<"
            attrs {
                onClickFunction = {
                    setIndex((index - 1).coerceAtLeast(0))
                }
            }
        }
        styledDiv {
            cssClasses("col-10", "col-centered")
            cykState {
                cykState = cykProps.cykStates[index]
            }
        }
        styledButton {
            cssClasses("btn", "btn-primary", "align-self-center", "col-1")
            +">"
            attrs {
                onClickFunction = {
                    setIndex((index + 1).coerceAtMost(cykProps.cykStates.lastIndex))
                }
            }
        }
    }
}

fun RBuilder.cykStateList(block: CYKStateListProps.() -> Unit) {
    child(cykStateList) {
        attrs.apply(block)
    }
}
