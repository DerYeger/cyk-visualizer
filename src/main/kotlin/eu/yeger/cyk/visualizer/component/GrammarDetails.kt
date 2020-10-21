package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.model.CYKState
import eu.yeger.cyk.model.CYKStep
import eu.yeger.cyk.visualizer.cssClasses
import kotlinx.css.FontWeight
import kotlinx.css.fontWeight
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import styled.*

external interface GrammarDetailsProps : RProps {
    var cykState: CYKState
}

val grammarDetailsComponent = functionalComponent<GrammarDetailsProps> {
    val state = it.cykState
    styledH4 {
        cssClasses("row", "mb-3")
        +"Grammar"
    }
    styledDiv {
        cssClasses("row", "mb-3")
        +"Start Symbol: ${state.cykModel.grammar.startSymbol}"
    }
    styledH5 {
        cssClasses("row", "mb-3")
        +"Production Rules"
    }
    state.cykModel.grammar.productionRuleSet.forEach { productionRule ->
        styledDiv {
            cssClasses("row", "mb-3")
            +productionRule.toString()
            if (state is CYKStep && state.productionRule == productionRule) {
                css {
                    fontWeight = FontWeight.bold
                }
            }
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
