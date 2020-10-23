package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.getOrElse
import eu.yeger.cyk.model.CYKState
import eu.yeger.cyk.parser.grammar
import eu.yeger.cyk.runningCYK
import eu.yeger.cyk.visualizer.cssClasses
import kotlinx.browser.window
import react.RProps
import react.functionalComponent
import react.useState
import styled.styledDiv
import styled.styledImg

val app = functionalComponent<RProps> {
    val (cykStates, setCYKStates) = useState<List<CYKState>>(emptyList())
    styledDiv {
        styledImg(alt = "CYK Visualizer", src = "cyk-visualizer-logo.png") {
            cssClasses("row", "mb-3", "mt-3")
            attrs {
                width = "128"
                height = "128"
            }
        }
        cssClasses("container", "w-75", "mx-auto")
        cykInput { word, startSymbol, productionRules, includeEmptyProductionRule ->
            val newCYKStates = runningCYK(word) {
                grammar(startSymbol, includeEmptyProductionRule = includeEmptyProductionRule) { productionRules }
            }.getOrElse {
                window.alert(it)
                emptyList()
            }
            setCYKStates(newCYKStates)
        }
        cykStateList(cykStates)
    }
}
