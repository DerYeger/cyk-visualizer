package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.model.CYKStart
import eu.yeger.cyk.model.CYKState
import eu.yeger.cyk.model.CYKStep
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
    val cykState = cykProps.cykStates[index]
    styledDiv {
        cssClasses("row", "justify-content-md-center", "mb-3")
        styledButton {
            cssClasses("btn", "btn-sm", "align-self-center", "col-1")
            +"<"
            attrs {
                onClickFunction = {
                    setIndex((index - 1).coerceAtLeast(0))
                }
            }
        }
        styledInput(type = InputType.range) {
            cssClasses("col-10", "col-centered", "px-0")
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
        styledButton {
            cssClasses("btn", "btn-sm", "align-self-center", "col-1")
            +">"
            attrs {
                onClickFunction = {
                    setIndex((index + 1).coerceAtMost(cykProps.cykStates.lastIndex))
                }
            }
        }
    }
    styledDiv {
        cssClasses("row", "mb-3")
        styledDiv {
            cssClasses("col-8", "pl-0")
            when (cykState) {
                is CYKStart -> cykStart(cykState)
                is CYKStep -> cykStep(cykState)
            }
        }
        styledDiv {
            cssClasses("col-4", "pr-0")
            grammarDetails(cykState)
        }
    }
}

fun RBuilder.cykStateList(cykStates: List<CYKState>) {
    child(cykStateList) {
        attrs.apply {
            this.cykStates = cykStates
            key = cykStates.lastOrNull().toString()
        }
    }
}
