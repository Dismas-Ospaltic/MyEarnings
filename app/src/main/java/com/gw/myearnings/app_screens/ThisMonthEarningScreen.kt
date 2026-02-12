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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gw.myearnings.app_screens.ui_components.SumCountDisplay
import com.gw.myearnings.utils.dateFormat
import com.gw.myearnings.utils.yearMonthFormat
import com.gw.myearnings.viewmodel.EarnedCashViewModel
import com.gw.myearnings.viewmodel.SettingsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun ThisMonthEarningScreen(navController: NavController) {
    val earnedCashViewModel: EarnedCashViewModel = koinViewModel()

     val today = dateFormat(System.currentTimeMillis())

    // Active earnings for today
    val earningsToday by earnedCashViewModel
        .getEarningsByDate(today)
        .collectAsStateWithLifecycle(initialValue = emptyList())

    val month = yearMonthFormat(today.toString())



    val viewModel: SettingsViewModel = koinViewModel()
    val selectedCurrency by viewModel.selectedCurrency.collectAsState()



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
                .padding(bottom = 95.dp)
                .background(colorResource(id = R.color.lavender))
                // 👇 THIS is the key line
                // It pushes CONTENT below the status bar
                .statusBarsPadding()


        ) {
            DateHeader()
            Spacer(modifier = Modifier.height(8.dp))
            SumCountDisplay()

            Spacer(modifier = Modifier.height(8.dp))


            // Total Earned (highlight)
            Text(
                text = "Today's Entry",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.charcoal_blue)
                )
            )

            if(earningsToday.isEmpty()){
               NoDataPlaceholder()
            }else{
                // Iterate over earnings when not empty
                for (index in earningsToday.indices) {
                    val earn = earningsToday[index]







//                }
//
//            }
//
//
//
//            repeat(5) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .clickable { },
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.platinum) // soft off-white
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        // Header: Date
                        Text(
                            text = "Date :${earn.date}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(id = R.color.dusk_blue)
                            )
                        )

                        // Total Earned (highlight)
                        Text(
                            text = "Earned: $selectedCurrency ${earn.totalEarned}",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(id = R.color.dusk_blue)
                            )
                        )

                        // Income / Saved / Spend row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            StatText(
                                label = "Saved",
                                value = "$selectedCurrency ${earn.totalSaved}",
                                valueColor = colorResource(id = R.color.dusk_blue)
                            )

                            StatText(
                                label = "Spent",
                                value = "$selectedCurrency ${earn.totalSpend}",
                                valueColor = colorResource(id = R.color.red)
                            )
                        }

                        // Source chip
                        Box(
                            modifier = Modifier
                                .background(
                                    color = colorResource(id = R.color.lavender),
                                    shape = RoundedCornerShape(50)
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "Source: ${earn.source}",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = colorResource(id = R.color.charcoal_blue),
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }


                        if (!earn.note.isNullOrBlank()) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = colorResource(id = R.color.pale_oak),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(12.dp)
                            ) {

                                Text(
                                    text = "Note",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = colorResource(id = R.color.charcoal_blue)
                                    )
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = earn.note, // Safe because we already checked
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = colorResource(id = R.color.lilac_ash)
                                    )
                                )
                            }
                        }

                    }
                }

            }
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun ThisMonthEarningScreenPreview() {
    ThisMonthEarningScreen(navController = rememberNavController())
}


@Composable
fun DateHeader(
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
                text = "Today's Date",
                fontSize = 14.sp,
                color = colorResource(id = R.color.charcoal_blue)
            )

            Text(
                text = dateFormat(System.currentTimeMillis()),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.dusk_blue)
            )
        }

    }
}


@Composable
fun StatText(
    label: String,
    value: String,
    valueColor: Color
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = colorResource(id = R.color.lilac_ash)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = valueColor
            )
        )
    }
}




///no data placeholder
@Composable
fun NoDataPlaceholder() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = colorResource(id = R.color.lavender).copy(alpha = 0.25f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 40.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "No Data Available",
            color = colorResource(id = R.color.dusk_blue),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}



