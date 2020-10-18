package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.model.CYKState
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent

external interface CYKStateListProps : RProps {
    var cykStates: List<CYKState>
}

val cykStateList = functionalComponent<CYKStateListProps> { cykProps ->
    cykProps.cykStates.forEach { cykState ->
        cykState {
            this.cykState = cykState
        }
    }
}

fun RBuilder.cykStateList(block: CYKStateListProps.() -> Unit) {
    child(cykStateList) {
        attrs.apply(block)
    }
}
