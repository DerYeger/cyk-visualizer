package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.model.CYKState
import react.RBuilder
import react.RProps
import react.child
import react.dom.pre
import react.functionalComponent

external interface CYKStateProps : RProps {
    var cykState: CYKState
}

val cykState = functionalComponent<CYKStateProps> { cykStateProps ->
    pre {
        +cykStateProps.cykState.cykModel.toString()
    }
}

fun RBuilder.cykState(block: CYKStateProps.() -> Unit) {
    child(cykState) {
        attrs.apply(block)
    }
}
