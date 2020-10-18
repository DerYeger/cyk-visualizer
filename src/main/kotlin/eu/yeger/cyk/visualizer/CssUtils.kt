package eu.yeger.cyk.visualizer

import styled.StyledBuilder
import styled.css

fun StyledBuilder<*>.cssClasses(vararg classes: String) {
    css {
        this.classes.addAll(classes)
    }
}
