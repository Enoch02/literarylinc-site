package com.enoch02.literarylinc

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color

/**
 * @property nearBackground A useful color to apply to a container that should differentiate itself from the background
 *   but just a little.
 */
class SitePalette(
    val nearBackground: Color,
    val brand: Brand,
) {
    class Brand(
        val primary: Color,
        val accent: Color,
    )
}

object SitePalettes {
    val light = SitePalette(
        nearBackground = Color.rgb(0xF5FAFD),
        brand = SitePalette.Brand(
            primary = Color.rgb(0x06677F),
            accent = Color.rgb(0x5A5C7E),
        )
    )
    val dark = SitePalette(
        nearBackground = Color.rgb(0x0F1416),
        brand = SitePalette.Brand(
            primary = Color.rgb(0x06677F),
            accent = Color.rgb(0xC3C3EB),
        )
    )
}

fun ColorMode.toSitePalette(): SitePalette {
    return when (this) {
        ColorMode.LIGHT -> SitePalettes.light
        ColorMode.DARK -> SitePalettes.dark
    }
}

@InitSilk
fun initTheme(ctx: InitSilkContext) {
    ctx.theme.palettes.light.background = Color.rgb(0xFAFAFA)
    ctx.theme.palettes.light.color = Colors.Black
    ctx.theme.palettes.dark.background = Color.rgb(0x06080B)
    ctx.theme.palettes.dark.color = Colors.White
}
