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
    val (cykList, setCYKList) = useState<List<CYKState>>(emptyList())

    styledDiv {
        cssClasses("w-75", "mx-auto")
        styledH1 {
            +"CYK Visualizer"
        }
        cykInput {
            onInputConfirmed = { word, startSymbol, productionRules ->
                val newCYKStates = runningCYK(word) {
                    grammar(startSymbol) { productionRules }
                }.getOrElse {
                    window.alert(it)
                    emptyList()
                }
                setCYKList(newCYKStates)
            }
        }
        cykStateList {
            cykStates = cykList
            key = cykList.firstOrNull().toString()
        }
    }
}
