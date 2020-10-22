package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.visualizer.cssClasses
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.*
import styled.styledButton
import styled.styledDiv
import styled.styledInput
import styled.styledLabel

external interface CYKInputProps : RProps {
    var onInputConfirmed: (String, String, String, Boolean) -> Unit
}

val cykInput = functionalComponent<CYKInputProps> { inputProps ->
    val (word, setWord) = useState("")
    val (startSymbol, setStartSymbol) = useState("")
    val (productionRules, setProductionRules) = useState("")
    val (includeEmptyProductionRule, setIncludeEmptyProductionRule) = useState(false)

    styledDiv {
        textInput(name = "Word", placeholder = "ε", onValueChange = setWord)
        textInput(name = "Start Symbol", placeholder = "S", onValueChange = setStartSymbol)
        textAreaInput(name = "Production Rules", placeholder = "S -> A B\nA -> hello\nB -> world", onValueChange = setProductionRules)
        styledDiv {
            cssClasses("row", "form-group", "form-check", "mb-3")
            styledInput(type = InputType.checkBox) {
                cssClasses("form-check-input")
                attrs {
                    id = "emptyProductionRuleToggle"
                    onChangeFunction = {
                        val target = it.target as HTMLInputElement
                        setIncludeEmptyProductionRule(target.checked)
                    }
                }
            }
            styledLabel {
                cssClasses("form-check-label")
                +"Include empty Production Rule"
                attrs {
                    htmlFor = "emptyProductionRuleToggle"
                }
            }
        }
        styledDiv {
            cssClasses("row", "mb-3")
            styledButton {
                cssClasses("btn", "btn-primary", "mr-3")
                +"Evaluate"
                attrs {
                    onClickFunction = {
                        inputProps.onInputConfirmed(word, startSymbol, productionRules, includeEmptyProductionRule)
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

fun RBuilder.cykInput(onInputConfirmed: (String, String, String, Boolean) -> Unit) {
    child(cykInput) {
        attrs.onInputConfirmed = onInputConfirmed
    }
}

private fun ((String, String, String, Boolean) -> Unit).invokeWithExampleData() {
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
    invoke(word, "S", productionRules, false)
}
