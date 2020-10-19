package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.model.CYKStart
import eu.yeger.cyk.model.CYKState
import eu.yeger.cyk.model.CYKStep
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import styled.styledPre

external interface CYKStateProps : RProps {
    var cykState: CYKState
}

val cykState = functionalComponent<CYKStateProps> { cykStateProps ->
    styledPre {
        when (val state = cykStateProps.cykState) {
            is CYKStart -> +"${state.cykModel}"
            is CYKStep -> +"${state.cykModel}\nCurrent Rule: ${state.productionRule}"
        }
    }
}

fun RBuilder.cykState(block: CYKStateProps.() -> Unit) {
    child(cykState) {
        attrs.apply(block)
    }
}
