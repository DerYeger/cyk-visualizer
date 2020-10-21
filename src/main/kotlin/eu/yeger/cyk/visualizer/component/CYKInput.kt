package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.visualizer.cssClasses
import kotlinx.html.js.onClickFunction
import react.*
import styled.styledButton
import styled.styledDiv

external interface CYKInputProps : RProps {
    var onInputConfirmed: (String, String, String) -> Unit
}

val cykInput = functionalComponent<CYKInputProps> { inputProps ->
    val (word, setWord) = useState("")
    val (startSymbol, setStartSymbol) = useState("")
    val (productionRules, setProductionRules) = useState("")

    styledDiv {
        cssClasses("container")
        textInput {
            name = "Word"
            onValueChange = setWord
        }
        textInput {
            name = "Start Symbol"
            onValueChange = setStartSymbol
        }
        textAreaInput {
            name = "Production Rules"
            onValueChange = setProductionRules
        }
        styledDiv {
            cssClasses("row", "justify-content-end")
            styledButton {
                cssClasses("btn", "btn-primary", "float-right")
                +"Evaluate"
                attrs {
                    onClickFunction = {
                        inputProps.onInputConfirmed(word, startSymbol, productionRules)
                    }
                }
            }
        }
    }
}

fun RBuilder.cykInput(block: CYKInputProps.() -> Unit) {
    child(cykInput) {
        attrs.apply(block)
    }
}
