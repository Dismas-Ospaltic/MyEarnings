package com.gw.myearnings.app_screens



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gw.myearnings.R
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gw.myearnings.utils.dateFormat




@Composable
fun MonthlyEarningScreen(navController: NavController) {

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
            MonthlyHeader()
            Spacer(modifier = Modifier.height(8.dp))

            repeat(5){

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .clickable { },
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.dusk_blue) // soft off-white
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        // Header: Date
                        Text(
                            text = "Date :Feb 2026",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(id = R.color.white)
                            )
                        )

                        // Total Earned (highlight)
                        Text(
                            text = "Earned: Kes 600",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(id = R.color.white)
                            )
                        )
                            StatText(
                                label = "Saved",
                                value = "Kes 600",
                                valueColor = colorResource(id = R.color.white)
                            )

                            StatText(
                                label = "Spent",
                                value = "Kes 0",
                                valueColor = colorResource(id = R.color.red)
                            )

                    }
                }


            }




        }
    }

}


@Preview(showBackground = true)
@Composable
fun MonthlyEarningScreenPreview() {
    MonthlyEarningScreen(navController = rememberNavController())
}


@Composable
fun MonthlyHeader(
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
                text = "Earnings Per Month",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.dusk_blue)
            )
        }

    }
}





