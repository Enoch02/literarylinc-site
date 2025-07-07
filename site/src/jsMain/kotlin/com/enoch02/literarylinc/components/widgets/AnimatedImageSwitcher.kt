package com.enoch02.literarylinc.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Animation
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.animation
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Text
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

val FadeIn = Keyframes {
    0.percent { Modifier.opacity(0.0) }
    100.percent { Modifier.opacity(1.0) }
}

val FadeOut = Keyframes {
    0.percent { Modifier.opacity(1.0) }
    100.percent { Modifier.opacity(0.0) }
}


@Composable
fun AnimatedImageSwitcher(
    imageUrl: String,
    description: String = "Screenshots",
    modifier: Modifier = Modifier
) {
    var previousImage by remember { mutableStateOf(imageUrl) }
    var fadingOut by remember { mutableStateOf(false) }

    LaunchedEffect(imageUrl) {
        if (imageUrl != previousImage) {
            fadingOut = true
            delay(300.milliseconds)
            previousImage = imageUrl
            fadingOut = false
        }
    }

    Box(modifier) {
        // Outgoing image
        if (fadingOut) {
            /*Img(
                src = previousImage,
                modifier = Modifier
                    .position(Position.Absolute)
                    .width(100.percent)
                    .animation(
                        FadeOut.toAnimation(
                            duration = 300.ms,
                            iterationCount = AnimationIterationCount.Once,
                            fillMode = AnimationFillMode.Forwards
                        )
                    )
            )*/
            Image(
                src = previousImage,
                description = description,
                height = 400,
                modifier = modifier
                    .animation(
                        FadeOut.toAnimation(
                            duration = 300.ms,
                            iterationCount = AnimationIterationCount.of(1),
                            timingFunction = AnimationTimingFunction.EaseInOut,
                            direction = AnimationDirection.Normal,
                            fillMode = AnimationFillMode.Forwards
                        )
                    )
            )
        }

        // Incoming image
        /*Img(
            src = imageUrl,
            modifier = Modifier
                .position(Position.Absolute)
                .width(100.percent)
                .animation(
                    FadeIn.toAnimation(
                        duration = 300.ms,
                        iterationCount = AnimationIterationCount.Once,
                        fillMode = AnimationFillMode.Forwards
                    )
                )
        )*/
        Image(
            src = imageUrl,
            description = description,
            height = 400,
            modifier = modifier
                .animation(
                    FadeIn.toAnimation(
                        duration = 300.ms,
                        iterationCount = AnimationIterationCount.of(1),
                        timingFunction = AnimationTimingFunction.EaseInOut,
                        direction = AnimationDirection.Normal,
                        fillMode = AnimationFillMode.Forwards
                    )
                )
        )
    }
}
