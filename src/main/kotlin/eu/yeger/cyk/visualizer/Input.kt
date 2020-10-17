package eu.yeger.cyk.visualizer

import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.RProps
import react.functionalComponent
import react.useState
import styled.styledButton
import styled.styledDiv
import styled.styledInput

external interface InputProps : RProps {
    var onInputConfirmed: (String, String, String) -> Unit
}

val inputComponent = functionalComponent<InputProps> { inputProps ->
    val (word, setWord) = useState("")
    val (startSymbol, setStartSymbol) = useState("")
    val (productionRules, setProductionRules) = useState("")

    styledDiv {
        styledInput(type = InputType.text) {
            attrs {
                placeholder = "Word"
                onChangeFunction = { event ->
                    val target = event.target as HTMLInputElement
                    setWord(target.value)
                }
            }
        }
        styledInput(type = InputType.text) {
            attrs {
                placeholder = "Start Symbol"
                onChangeFunction = {
                    val target = it.target as HTMLInputElement
                    setStartSymbol(target.value)
                }
            }
        }
        styledInput(type = InputType.text) {
            attrs {
                placeholder = "Production Rules"
                onChangeFunction = {
                    val target = it.target as HTMLInputElement
                    setProductionRules(target.value)
                }
            }
        }
        styledButton {
            +"Confirm"
            attrs {
                onClickFunction = {
                    println("clicked")
                    inputProps.onInputConfirmed(word, startSymbol, productionRules)
                }
            }
        }
    }
}
