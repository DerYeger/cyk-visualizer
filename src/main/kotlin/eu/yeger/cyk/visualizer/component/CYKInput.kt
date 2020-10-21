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
            cssClasses("row", "mb-3")
            styledButton {
                cssClasses("btn", "btn-primary", "mr-3")
                +"Evaluate"
                attrs {
                    onClickFunction = {
                        inputProps.onInputConfirmed(word, startSymbol, productionRules)
                    }
                }
            }
            styledButton {
                cssClasses("btn", "btn-primary")
                +"Example"
                attrs {
                    onClickFunction = {
                        inputProps.onInputConfirmed.invokeWithExampleData()
                    }
                }
            }
        }
    }
}

fun RBuilder.cykInput(onInputConfirmed: (String, String, String) -> Unit) {
    child(cykInput) {
        attrs.onInputConfirmed = onInputConfirmed
    }
}

private fun ((String, String, String) -> Unit).invokeWithExampleData() {
    val word = "she eats a fish with a fork"
    val productionRules =
        """
                    S -> NP VP
                    VP -> VP PP
                    VP -> V NP
                    VP -> eats
                    PP -> P NP
                    NP -> Det N
                    NP -> she
                    V -> eats
                    P -> with
                    N -> fish
                    N -> fork
                    Det -> a
        """.trimIndent()
    invoke(word, "S", productionRules)
}
