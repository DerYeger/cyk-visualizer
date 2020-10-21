package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.model.CYKState
import eu.yeger.cyk.visualizer.cssClasses
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.*
import styled.styledButton
import styled.styledDiv
import styled.styledInput

external interface CYKStateListProps : RProps {
    var cykStates: List<CYKState>
}

val cykStateList = functionalComponent<CYKStateListProps> { cykProps ->
    val (index, setIndex) = useState(cykProps.cykStates.lastIndex)
    if (cykProps.cykStates.isEmpty()) return@functionalComponent
    styledDiv {
        cssClasses("row", "justify-content-md-center")
        styledInput(type = InputType.range) {
            cssClasses("w-100")
            attrs {
                min = "0"
                max = "${cykProps.cykStates.lastIndex}"
                value = "$index"
                onChangeFunction = {
                    val target = it.target as HTMLInputElement
                    setIndex(target.value.toInt())
                }
            }
        }
    }
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
