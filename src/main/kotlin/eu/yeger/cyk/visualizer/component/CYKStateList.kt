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
    val cykStates = cykProps.cykStates
    val (index, setIndex) = useState(cykStates.lastIndex)
    val (stopTimer, setStopTimer) = useState(false)

    val updateIndex = { newIndex: Int ->
        setIndex(newIndex.coerceIn(0..cykStates.lastIndex))
    }

    if (cykStates.isEmpty()) return@functionalComponent
    val cykState = cykStates[index]
    styledDiv {
        cssClasses("row", "justify-content-md-center", "mb-3")
        styledButton {
            cssClasses("btn", "btn-sm", "align-self-center", "col-1")
            +"<"
            attrs {
                onClickFunction = {
                    setStopTimer(true)
                    updateIndex(index - 1)
                }
            }
        }
        styledInput(type = InputType.range) {
            cssClasses("col-10", "col-centered", "px-0")
            attrs {
                min = "0"
                max = "${cykStates.lastIndex}"
                value = "$index"
                onChangeFunction = {
                    val target = it.target as HTMLInputElement
                    setStopTimer(true)
                    updateIndex(target.value.toInt())
                }
            }
        }
        styledButton {
            cssClasses("btn", "btn-sm", "align-self-center", "col-1")
            +">"
            attrs {
                onClickFunction = {
                    setStopTimer(true)
                    updateIndex(index + 1)
                }
            }
        }
    }
    styledDiv {
        cssClasses("row", "mb-3")
        styledDiv {
            cssClasses("col-sm-8", "pl-0")
            cykState(cykState)
        }
        styledDiv {
            cssClasses("col-sm-4", "pr-0")
            styledDiv {
                cssClasses("row", "mb-3")
                toggleTimer(
                    interval = 500,
                    onStart = {
                        if (index == cykStates.lastIndex) {
                            setIndex(0)
                        }
                    },
                    onTick = {
                        updateIndex(index + 1)
                        if (index == cykStates.lastIndex) {
                            setStopTimer(true)
                        }
                    },
                    stopTimer = stopTimer,
                    onTimerStopped = { setStopTimer(false) }
                )
            }
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
