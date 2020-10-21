package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.model.CYKStart
import eu.yeger.cyk.model.CYKStep
import eu.yeger.cyk.visualizer.cssClasses
import kotlinx.css.WhiteSpace
import kotlinx.css.whiteSpace
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import styled.*

external interface CYKStartProps : RProps {
    var cykStart: CYKStart
}

val cykStartComponent = functionalComponent<CYKStartProps> {
    with(it.cykStart.cykModel) {
        styledDiv {
            cssClasses("table-responsive")
            styledTable {
                cssClasses("table", "table-bordered")
                styledThead {
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
}

fun RBuilder.cykStart(cykStart: CYKStart) {
    child(cykStartComponent) {
        attrs.apply {
            this.cykStart = cykStart
        }
    }
}

external interface CYKStepProps : RProps {
    var cykStep: CYKStep
}

val cykStepComponent = functionalComponent<CYKStepProps> {
    val cykStep = it.cykStep
    with(cykStep.cykModel) {
        styledDiv {
            cssClasses("table-responsive")
            styledTable {
                cssClasses("table", "table-bordered")
                styledThead {
                    styledTr {
                        word.forEachIndexed { index, terminalSymbol ->
                            styledTh {
                                +terminalSymbol.toString()
                                if (cykStep.targetCoordinates.any { coordinates ->
                                    coordinates.row == -1 && coordinates.column == index
                                }
                                ) {
                                    cssClasses("table-primary")
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
                                    if (cykStep.targetCoordinates.any { coordinates ->
                                        coordinates.row == rowIndex && coordinates.column == columnIndex
                                    }
                                    ) {
                                        cssClasses("table-primary")
                                    } else if (cykStep.sourceCoordinates.row == rowIndex && cykStep.sourceCoordinates.column == columnIndex) {
                                        cssClasses(if (cykStep.ruleWasApplied) "table-success" else "table-danger")
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

fun RBuilder.cykStep(cykStep: CYKStep) {
    child(cykStepComponent) {
        attrs.apply {
            this.cykStep = cykStep
        }
    }
}
