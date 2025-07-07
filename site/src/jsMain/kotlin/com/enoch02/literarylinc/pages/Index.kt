package com.enoch02.literarylinc.pages

import androidx.compose.runtime.*
import com.enoch02.literarylinc.components.layouts.PageLayoutData
import com.enoch02.literarylinc.components.widgets.AnimatedImageSwitcher
import com.enoch02.literarylinc.components.widgets.IconButton
import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.ArrowBackIcon
import com.varabyte.kobweb.silk.components.icons.ArrowForwardIcon
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorPalettes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import kotlin.time.Duration.Companion.seconds

// Container that has a tagline and grid on desktop, and just the tagline on mobile
val HeroContainerStyle = CssStyle {
    base { Modifier.fillMaxWidth().gap(2.cssRem) }
    Breakpoint.MD { Modifier.margin { top(20.vh) } }
}

// A demo grid that appears on the homepage because it looks good
val HomeGridStyle = CssStyle.base {
    Modifier
        .gap(0.5.cssRem)
        .width(70.cssRem)
        .height(18.cssRem)
}

private val GridCellColorVar by StyleVariable<Color>()
val HomeGridCellStyle = CssStyle.base {
    Modifier
        .backgroundColor(GridCellColorVar.value())
        .boxShadow(blurRadius = 0.6.cssRem, color = GridCellColorVar.value())
        .borderRadius(1.cssRem)
}

@Composable
private fun GridCell(color: Color, row: Int, column: Int, width: Int? = null, height: Int? = null) {
    Div(
        HomeGridCellStyle.toModifier()
            .setVariable(GridCellColorVar, color)
            .gridItem(row, column, width, height)
            .toAttrs()
    )
}


@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Home"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun HomePage() {
    //TODO: fix widths for mobile
    Row {
        Column(modifier = Modifier.gap(1.cssRem), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                src = "/feature_graphic.png",
                description = "LiteraryLinc Graphic",
                modifier = Modifier.height(15.cssRem)
            )

            val ctx = rememberPageContext()
            Button(
                onClick = {
                    //TODO: replace with universal apk github link
                    ctx.router.navigateTo("https://github.com/Enoch02/LiteraryLinc/releases/download/v0.0.14/Literarylinc-universal-release.apk")
                },
                colorPalette = ColorPalettes.Blue,
                content = {
                    Text("Download APK")
                }
            )
        }

        Spacer()

        Row(
            modifier = Modifier.displayIfAtLeast(Breakpoint.MD),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                val coroutineScope = rememberCoroutineScope()
                val screenShotUrls = listOf(
                    "/app_screenshot1.jpg",
                    "/app_screenshot2.jpg",
                    "/app_screenshot3.jpg",
                    "/app_screenshot4.jpg",
                )
                var currentImage by remember { mutableStateOf(0) }
                val getNextImage = {
                    if (currentImage == screenShotUrls.lastIndex) {
                        currentImage = 0
                    } else {
                        currentImage++
                    }
                }

                LaunchedEffect(currentImage) {
                    delay(5.seconds)
                    getNextImage()
                }

                IconButton(
                    onClick = {
                        if (currentImage == 0) {
                            currentImage = screenShotUrls.lastIndex
                        } else {
                            currentImage--
                        }
                    },
                    content = { ArrowBackIcon() }
                )

                Box(
                    modifier = Modifier.boxShadow(blurRadius = 5.px, spreadRadius = 3.px, color = Colors.DarkGray),
                    content = {
                        AnimatedImageSwitcher(screenShotUrls[currentImage])
                    }
                )

                IconButton(
                    onClick = { getNextImage() },
                    content = { ArrowForwardIcon() }
                )
            }
        )
    }
}
