package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.model.CYKState
import eu.yeger.cyk.visualizer.cssClasses
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import styled.styledDiv
import styled.styledH5
import styled.styledH6

external interface GrammarDetailsProps : RProps {
    var cykState: CYKState
}

val grammarDetailsComponent = functionalComponent<GrammarDetailsProps> {
    val state = it.cykState
    styledH5 {
        cssClasses("row", "mb-3")
        +"Grammar"
    }
    styledDiv {
        cssClasses("row", "mb-3")
        +"Start Symbol: ${state.cykModel.grammar.startSymbol}"
    }
    styledH6 {
        cssClasses("row", "mb-3")
        +"Production Rules"
    }
    state.cykModel.grammar.productionRuleSet.forEach { productionRule ->
        styledDiv {
            cssClasses("row", "mb-3")
            +productionRule.toString()
        }
    }
}

fun RBuilder.grammarDetails(cykState: CYKState) {
    child(grammarDetailsComponent) {
        attrs {
            this.cykState = cykState
        }
    }
}
