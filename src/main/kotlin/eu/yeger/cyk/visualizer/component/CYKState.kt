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

val cykStateComponent = functionalComponent<CYKStateProps> {
    val cykState = it.cykState
    with(cykState.model) {
        styledDiv {
            cssClasses("table-responsive")
            styledTable {
                cssClasses("table", "table-bordered")
                styledThead {
                    styledTr {
                        word.forEachIndexed { index, terminalSymbol ->
                            styledTh {
                                +terminalSymbol.toString()
                                if (cykState is CYKState.Step && cykState.targetCoordinates.any { coordinates ->
                                    coordinates.row == -1 && coordinates.column == index
                                }
                                ) {
                                    cssClasses("table-primary")
                                } else if (cykState is CYKState.Done) {
                                    cssClasses(if (cykState.wordIsMemberOfLanguage) "table-success" else "table-danger")
                                }
                            }
                        }
                    }
                }
                styledTbody {
                    grid.forEachIndexed { rowIndex, row ->
                        styledTr {
                            row.forEachIndexed { columnIndex, nonTerminalSymbolSet ->
                                styledTd {
                                    css {
                                        whiteSpace = WhiteSpace.pre
                                    }
                                    +nonTerminalSymbolSet.joinToString(", ").ifEmpty { " " }
                                    if (cykState is CYKState.Step) {
                                        if (cykState.targetCoordinates.any { coordinates ->
                                            coordinates.row == rowIndex && coordinates.column == columnIndex
                                        }
                                        ) {
                                            // is target of production rule
                                            cssClasses("table-primary")
                                        } else if (cykState.sourceCoordinates.row == rowIndex && cykState.sourceCoordinates.column == columnIndex) {
                                            // is source of production rule
                                            cssClasses(if (cykState.productionRuleWasApplied) "table-success" else "table-danger")
                                        }
                                    } else if (cykState is CYKState.Done && rowIndex == grid.lastIndex) {
                                        cssClasses(if (cykState.wordIsMemberOfLanguage) "table-success" else "table-danger")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.cykState(cykState: CYKState) {
    child(cykStateComponent) {
        attrs.apply {
            this.cykState = cykState
        }
    }
}
