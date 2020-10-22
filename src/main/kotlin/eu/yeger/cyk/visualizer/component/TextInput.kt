package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.visualizer.cssClasses
import kotlinx.css.*
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import styled.*

external interface TextInputProps : RProps {
    var name: String
    var placeholder: String
    var onValueChange: (String) -> Unit
}

val textInput = functionalComponent<TextInputProps> { textInputProps ->
    styledDiv {
        cssClasses("row", "input-group", "mb-3")
        styledDiv {
            cssClasses("input-group-prepend")
            styledSpan {
                css {
                    display = Display.block
                    minWidth = 115.13.px
                    textAlign = TextAlign.center
                }
                cssClasses("input-group-text")
                +textInputProps.name
            }
        }
        styledInput(type = InputType.text) {
            cssClasses("form-control")
            attrs {
                placeholder = textInputProps.placeholder
                onChangeFunction = {
                    val target = it.target as HTMLInputElement
                    textInputProps.onValueChange(target.value)
                }
            }
        }
    }
}

fun RBuilder.textInput(name: String, placeholder: String, onValueChange: (String) -> Unit) {
    child(textInput) {
        attrs.apply {
            this.name = name
            this.placeholder = placeholder
            this.onValueChange = onValueChange
        }
    }
}

val textAreaInput = functionalComponent<TextInputProps> { textInputProps ->
    styledDiv {
        cssClasses("row", "input-group", "mb-3")
        styledDiv {
            cssClasses("input-group-prepend")
            styledSpan {
                cssClasses("input-group-text")
                css {
                    display = Display.block
                    minWidth = 115.13.px
                    whiteSpace = WhiteSpace.pre
                    textAlign = TextAlign.center
                }
                +textInputProps.name
            }
        }
        styledTextArea {
            cssClasses("form-control")
            attrs {
                rows = "4"
                placeholder = textInputProps.placeholder
                onChangeFunction = {
                    val target = it.target as HTMLTextAreaElement
                    textInputProps.onValueChange(target.value)
                }
            }
        }
    }
}

fun RBuilder.textAreaInput(name: String, placeholder: String, onValueChange: (String) -> Unit) {
    child(textAreaInput) {
        attrs.apply {
            this.name = name
            this.placeholder = placeholder
            this.onValueChange = onValueChange
        }
    }
}
