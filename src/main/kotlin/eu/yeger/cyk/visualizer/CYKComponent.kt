package eu.yeger.cyk.visualizer

import eu.yeger.cyk.model.CYKState
import react.RProps
import react.dom.p
import react.functionalComponent

external interface CYKProps : RProps {
    var cykStates: List<CYKState>
}

val cykStateList = functionalComponent<CYKProps> { cykProps ->
    cykProps.cykStates.forEach { cykState ->
        p {
            +"$cykState"
        }
    }
}
