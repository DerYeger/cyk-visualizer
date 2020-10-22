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
        textAreaInput(name = "Production\nRules", placeholder = "S -> A B\nA -> hello\nB -> world", onValueChange = setProductionRules)
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
                    attributes["htmlFor"] = "emptyProductionRuleToggle"
                }
            }
        }
        styledDiv {
            cssClasses("row")
            styledButton {
                cssClasses("btn", "btn-primary", "col-sm-2", "mr-3", "mb-3")
                +"Evaluate"
                attrs {
                    onClickFunction = {
                        inputProps.onInputConfirmed(word, startSymbol, productionRules, includeEmptyProductionRule)
                    }
                }
            }
            styledButton {
                cssClasses("btn", "btn-secondary", "col-sm-2", "mr-3", "mb-3")
                +"Example 1"
                attrs {
                    onClickFunction = {
                        inputProps.onInputConfirmed.invokeWithFirstExampleGrammar()
                    }
                }
            }
            styledButton {
                cssClasses("btn", "btn-secondary", "col-sm-2", "mr-3", "mb-3")
                +"Example 2"
                attrs {
                    onClickFunction = {
                        inputProps.onInputConfirmed.invokeWithSecondExampleGrammar()
                    }
                }
            }
            styledButton {
                cssClasses("btn", "btn-secondary", "col-sm-2", "mr-3", "mb-3")
                +"Example 3"
                attrs {
                    onClickFunction = {
                        inputProps.onInputConfirmed.invokeWithThirdExampleGrammar()
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

private fun ((String, String, String, Boolean) -> Unit).invokeWithFirstExampleGrammar() {
    val word = "hello world"
    val productionRules =
        """
                    S -> A B
                    A -> hello
                    B -> world
        """.trimIndent()
    invoke(word, "S", productionRules, false)
}

private fun ((String, String, String, Boolean) -> Unit).invokeWithSecondExampleGrammar() {
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

private fun ((String, String, String, Boolean) -> Unit).invokeWithThirdExampleGrammar() {
    val word = "she eats a fish with a fork"
    val productionRules =
        """
                    S -> NP PP
                    VP -> NP VP
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
