package eu.yeger.cyk.visualizer.component

import eu.yeger.cyk.visualizer.cssClasses
import kotlinx.browser.window
import kotlinx.html.js.onClickFunction
import react.*
import styled.styledButton

external interface ToggleTimerProps : RProps {
    var interval: Int
    var onStart: () -> Unit
    var onTick: () -> Unit
    var stopTimer: Boolean
    var onTimerStopped: () -> Unit
}

val toggleTimerComponent = functionalComponent<ToggleTimerProps> { toggleTimerProps ->
    val (isOn, setIsOn) = useState(false)
    useEffect(listOf(isOn, toggleTimerProps.stopTimer)) {
        if (toggleTimerProps.stopTimer) {
            toggleTimerProps.onTimerStopped() // always confirm that the timer has been stopped
            if (isOn) {
                setIsOn(false) // only stop it, when it was actually on
            }
        }
    }
    useEffectWithCleanup {
        val timeoutId = window.setTimeout(timeout = toggleTimerProps.interval, handler = { if (isOn) toggleTimerProps.onTick() })
        return@useEffectWithCleanup {
            window.clearTimeout(timeoutId)
        }
    }
    styledButton {
        cssClasses("btn", "btn-primary")
        +(if (isOn) "Stop Autoplay" else "Start Autoplay")
        attrs {
            onClickFunction = {
                if (!isOn) {
                    toggleTimerProps.onStart()
                }
                setIsOn(!isOn)
            }
        }
    }
}

fun RBuilder.toggleTimer(
    interval: Int,
    onStart: () -> Unit,
    onTick: () -> Unit,
    stopTimer: Boolean,
    onTimerStopped: () -> Unit,
) {
    child(toggleTimerComponent) {
        attrs.apply {
            this.interval = interval
            this.onStart = onStart
            this.onTick = onTick
            this.stopTimer = stopTimer
            this.onTimerStopped = onTimerStopped
        }
    }
}
