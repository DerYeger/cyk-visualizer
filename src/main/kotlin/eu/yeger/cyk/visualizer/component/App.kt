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
import styled.styledH1

val app = functionalComponent<RProps> {
    val (cykStates, setCYKStates) = useState<List<CYKState>>(emptyList())

    styledDiv {
        cssClasses("container", "w-75", "mx-auto")
        styledH1 {
            cssClasses("row")
            +"CYK Visualizer"
        }
        cykInput { word, startSymbol, productionRules ->
            val newCYKStates = runningCYK(word) {
                grammar(startSymbol) { productionRules }
            }.getOrElse {
                window.alert(it)
                emptyList()
            }
            setCYKStates(newCYKStates)
        }
        cykStateList(cykStates)
    }
}
