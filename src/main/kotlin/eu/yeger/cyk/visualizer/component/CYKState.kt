package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.model.CYKState
import eu.yeger.cyk.visualizer.cssClasses
import kotlinx.css.WhiteSpace
import kotlinx.css.whiteSpace
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import styled.*

external interface CYKStateProps : RProps {
    var cykState: CYKState
}

val cykState = functionalComponent<CYKStateProps> { cykStateProps ->
    with(cykStateProps.cykState.cykModel) {
        styledTable {
            cssClasses("table", "table-bordered")
            styledThead {
                cssClasses("thead-dark")
                styledTr {
                    word.forEach { terminalSymbol ->
                        styledTh {
                            +terminalSymbol.toString()
                        }
                    }
                }
            }
            styledTbody {
                grid.forEach { row ->
                    styledTr {
                        row.forEach { nonTerminalSymbolSet ->
                            styledTd {
                                styledDiv {
                                    css {
                                        whiteSpace = WhiteSpace.pre
                                    }
                                    +nonTerminalSymbolSet.joinToString(", ").ifEmpty { " " }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.cykState(block: CYKStateProps.() -> Unit) {
    child(cykState) {
        attrs.apply(block)
    }
}
