package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.visualizer.cssClasses
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import styled.styledDiv
import styled.styledInput
import styled.styledSpan
import styled.styledTextArea

external interface TextInputProps : RProps {
    var name: String
    var onValueChange: (String) -> Unit
}

val textInput = functionalComponent<TextInputProps> { textInputProps ->
    styledDiv {
        cssClasses("input-group mb-3")
        styledDiv {
            cssClasses("input-group-prepend")
            styledSpan {
                cssClasses("input-group-text")
                +textInputProps.name
            }
        }
        styledInput(type = InputType.text) {
            cssClasses("form-control")
            attrs {
                placeholder = textInputProps.name
                onChangeFunction = {
                    val target = it.target as HTMLInputElement
                    textInputProps.onValueChange(target.value)
                }
            }
        }
    }
}

fun RBuilder.textInput(block: TextInputProps.() -> Unit) {
    child(textInput) {
        attrs.apply(block)
    }
}

val textAreaInput = functionalComponent<TextInputProps> { textInputProps ->
    styledDiv {
        cssClasses("input-group mb-3")
        styledDiv {
            cssClasses("input-group-prepend")
            styledSpan {
                cssClasses("input-group-text")
                +textInputProps.name
            }
        }
        styledTextArea {
            cssClasses("form-control")
            attrs {
                rows = "4"
                placeholder = textInputProps.name
                onChangeFunction = {
                    val target = it.target as HTMLTextAreaElement
                    textInputProps.onValueChange(target.value)
                }
            }
        }
    }
}

fun RBuilder.textAreaInput(block: TextInputProps.() -> Unit) {
    child(textAreaInput) {
        attrs.apply(block)
    }
}
