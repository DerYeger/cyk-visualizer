package eu.yeger.cyk.visualizer

import eu.yeger.cyk.getOrElse
import eu.yeger.cyk.model.CYKState
import eu.yeger.cyk.parser.grammar
import eu.yeger.cyk.runningCYK
import kotlinx.browser.window
import react.RProps
import react.child
import react.dom.h1
import react.functionalComponent
import react.useState

val app = functionalComponent<RProps> {
    val (cykList, setCYKList) = useState<List<CYKState>>(emptyList())

    h1 {
        +"CYK Visualizer"
    }
    child(inputComponent) {
        attrs.onInputConfirmed = { word, startSymbol, productionRules ->
            println("triggered")
            val newCYKStates = runningCYK(word) {
                grammar(startSymbol) { productionRules }
            }.getOrElse {
                window.alert(it)
                emptyList()
            }
            setCYKList(newCYKStates)
        }
    }
    child(cykStateList) {
        attrs.cykStates = cykList
    }
}
