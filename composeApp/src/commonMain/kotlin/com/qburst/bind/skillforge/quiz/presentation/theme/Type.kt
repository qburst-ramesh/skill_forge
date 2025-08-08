package com.qburst.bind.skillforge.quiz.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.lato_black
import kotlinproject.composeapp.generated.resources.lato_black_italic
import kotlinproject.composeapp.generated.resources.lato_bold
import kotlinproject.composeapp.generated.resources.lato_bold_italic
import kotlinproject.composeapp.generated.resources.lato_italic
import kotlinproject.composeapp.generated.resources.lato_light
import kotlinproject.composeapp.generated.resources.lato_light_italic
import kotlinproject.composeapp.generated.resources.lato_regular
import kotlinproject.composeapp.generated.resources.lato_thin
import kotlinproject.composeapp.generated.resources.lato_thin_italic
import org.jetbrains.compose.resources.Font


@Composable
fun LatoTypography(): Typography {

    val lato = FontFamily(
        Font(
            resource = Res.font.lato_regular,
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.lato_thin,
            weight = FontWeight.Thin,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.lato_italic,
            weight = FontWeight.Normal,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.lato_light_italic,
            weight = FontWeight.Light,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.lato_thin_italic,
            weight = FontWeight.Thin,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.lato_bold_italic,
            weight = FontWeight.Bold,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.lato_black_italic,
            weight = FontWeight.Black,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.lato_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.lato_black,
            weight = FontWeight.Black,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.lato_light,
            weight = FontWeight.Light,
            style = FontStyle.Normal
        )
    )

    return Typography(
        headlineSmall = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = FontSize.size_24,
            fontFamily = lato
        ),
        titleLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = FontSize.size_18,
            fontFamily = lato
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = FontSize.size_16,
            fontFamily = lato
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = FontSize.size_8,
            fontFamily = lato
        ),
        labelMedium = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = FontSize.size_12,
            fontFamily = lato
        ),
    )
}

//
//@Composable
//fun StallionTypography(): Typography {
//    val stallion = FontFamily(
//        Font(
//            resource = Res.font.stallion_beatsides_regular,
//            weight = FontWeight.Normal,
//            style = FontStyle.Normal
//        ),
//    )
//
//    return Typography(
//        headlineSmall = TextStyle(
//            fontWeight = FontWeight.SemiBold,
//            fontSize = FontSize.size_24,
//            fontFamily = stallion
//        ),
//        titleLarge = TextStyle(
//            fontWeight = FontWeight.Normal,
//            fontSize = FontSize.size_18,
//            fontFamily = stallion
//        ),
//        bodyLarge = TextStyle(
//            fontWeight = FontWeight.Normal,
//            fontSize = FontSize.size_16,
//            fontFamily = stallion
//        ),
//        bodyMedium = TextStyle(
//            fontWeight = FontWeight.Medium,
//            fontSize = FontSize.size_8,
//            fontFamily = stallion
//        ),
//        labelMedium = TextStyle(
//            fontWeight = FontWeight.SemiBold,
//            fontSize = FontSize.size_12,
//            fontFamily = stallion
//        ),
//    )
//}
