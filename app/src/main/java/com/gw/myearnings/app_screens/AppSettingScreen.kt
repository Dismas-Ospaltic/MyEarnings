package com.gw.myearnings.app_screens



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import com.gw.myearnings.R
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gw.myearnings.utils.dateFormat
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ChevronRight
import compose.icons.fontawesomeicons.solid.Cogs
import compose.icons.fontawesomeicons.solid.CommentDots
import compose.icons.fontawesomeicons.solid.InfoCircle
import compose.icons.fontawesomeicons.solid.Shower
import compose.icons.fontawesomeicons.solid.UserPlus
import compose.icons.fontawesomeicons.solid.Wrench


@Composable
fun AppSettingScreen(navController: NavController) {

    val CardBg = colorResource(id = R.color.charcoal_blue)
    val ItemHover = colorResource(id = R.color.dusk_blue).copy(alpha = 0.25f)
    val TextPrimary = colorResource(id = R.color.platinum)
    val IconMuted = colorResource(id = R.color.lilac_ash)
    val AccentDot = colorResource(id = R.color.red)

    Column(
        modifier = Modifier
            .fillMaxSize()

            // Scrollable content
            .verticalScroll(rememberScrollState())


            .background(colorResource(id = R.color.lavender))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 16.dp)
                .background(colorResource(id = R.color.lavender))
                // 👇 THIS is the key line
                // It pushes CONTENT below the status bar
                .statusBarsPadding()
        ) {
            SettingHeader()
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        color = CardBg,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(vertical = 8.dp)
            ) {

                SettingsItem(
                    icon = FontAwesomeIcons.Solid.Cogs,
                    title = "Settings"
                )

                SettingsItem(
                    icon = FontAwesomeIcons.Solid.CommentDots,
                    title = "Help & Feedback"
                )

                SettingsItem(
                    icon = FontAwesomeIcons.Solid.Shower,
                    title = "Share Muso"
                )

                SettingsItem(
                    icon = FontAwesomeIcons.Solid.Wrench,
                    title = "Fix Songs",
                    showDot = true
                )

                SettingsItem(
                    icon = FontAwesomeIcons.Solid.UserPlus,
                    title = "Join our group"
                )

                SettingsItem(
                    icon = FontAwesomeIcons.Solid.InfoCircle,
                    title = "About us"
                )

//                SettingsSwitchItem(
//                    icon = FontAwesomeIcons.Solid.Dog,
//                    title = "Music Pet"
//                )
            }
            Spacer(modifier = Modifier.height(8.dp))




        }
    }

}



@Composable
fun SettingHeader(
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.pale_oak),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 12.dp
            )


    ) {

        // LEFT SIDE TEXT
        Column(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {

            Text(
                text = "App's Settings",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.dusk_blue)
            )
        }

    }
}




@Preview(showBackground = true)
@Composable
fun AppSettingScreenPreview() {
    AppSettingScreen(navController = rememberNavController())
}




//@Composable
//fun SettingsItem(
//    icon: ImageVector,
//    title: String,
//    showDot: Boolean = false
//) {
//    var isHovered by remember { mutableStateOf(false) }
//
//    val CardBg = colorResource(id = R.color.charcoal_blue)
//    val ItemHover = colorResource(id = R.color.dusk_blue).copy(alpha = 0.25f)
//    val TextPrimary = colorResource(id = R.color.platinum)
//    val IconMuted = colorResource(id = R.color.lilac_ash)
//    val AccentDot = colorResource(id = R.color.red)
//
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .pointerMoveFilter(
//                onEnter = {
//                    isHovered = true
//                    false
//                },
//                onExit = {
//                    isHovered = false
//                    false
//                }
//            )
//            .background(
//                if (isHovered) ItemHover else Color.Transparent,
//                RoundedCornerShape(16.dp)
//            )
//            .padding(horizontal = 16.dp, vertical = 14.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//
//        Icon(
//            imageVector = icon,
//            contentDescription = title,
//            tint = IconMuted,
//            modifier = Modifier.size(20.dp)
//        )
//
//        Spacer(modifier = Modifier.width(16.dp))
//
//        Text(
//            text = title,
//            color = TextPrimary,
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Medium,
//            modifier = Modifier.weight(1f)
//        )
//
//        if (showDot) {
//            Box(
//                modifier = Modifier
//                    .size(8.dp)
//                    .background(AccentDot, CircleShape)
//            )
//            Spacer(modifier = Modifier.width(12.dp))
//        }
//
//        Icon(
//            imageVector = FontAwesomeIcons.Solid.ChevronRight,
//            contentDescription = null,
//            tint = IconMuted,
//            modifier = Modifier.size(14.dp)
//        )
//    }
//}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    showDot: Boolean = false
) {
    var isHovered by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .hoverableAndroid { isHovered = it }
            .background(
                if (isHovered)
                    colorResource(id = R.color.dusk_blue).copy(alpha = 0.25f)
                else Color.Transparent,
                RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = colorResource(id = R.color.lilac_ash),
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            color = colorResource(id = R.color.platinum),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )

        if (showDot) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        colorResource(id = R.color.red),
                        CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(12.dp))
        }

        Icon(
            imageVector = FontAwesomeIcons.Solid.ChevronRight,
            contentDescription = null,
            tint = colorResource(id = R.color.lilac_ash),
            modifier = Modifier.size(14.dp)
        )
    }
}



fun Modifier.hoverableAndroid(
    onHoverChanged: (Boolean) -> Unit
) = pointerInput(Unit) {
    awaitPointerEventScope {
        while (true) {
            val event = awaitPointerEvent()
            when (event.type) {
                PointerEventType.Enter -> onHoverChanged(true)
                PointerEventType.Exit -> onHoverChanged(false)
                else -> Unit
            }
        }
    }
}


