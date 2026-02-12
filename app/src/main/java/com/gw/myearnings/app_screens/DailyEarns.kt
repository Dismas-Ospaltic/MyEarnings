package com.gw.myearnings.app_screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gw.myearnings.utils.DateUtils
import com.gw.myearnings.utils.dateFormat
import com.gw.myearnings.utils.yearMonthFormat
import com.gw.myearnings.viewmodel.EarnedCashViewModel
import com.gw.myearnings.viewmodel.SettingsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun DailyEarnsScreen(navController: NavController, date: String) {

    val earnedCashViewModel: EarnedCashViewModel = koinViewModel()

//    val today = dateFormat(System.currentTimeMillis())

    // Active earnings for today
    val earningsToday by earnedCashViewModel
        .getEarningsByDate(date)
        .collectAsStateWithLifecycle(initialValue = emptyList())


    // Monthly reports
    val reports by earnedCashViewModel
        .getMonthlyReports()
        .collectAsStateWithLifecycle(initialValue = emptyList())

    val month = yearMonthFormat(date.toString())

    val monthWord = DateUtils.formatYearMonth(month.toString())



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
                .background(colorResource(id = R.color.lavender))
                // 👇 THIS is the key line
                // It pushes CONTENT below the status bar
                .statusBarsPadding()
        ) {
            MonthlyHeaderDaily(monthWord)
            Spacer(modifier = Modifier.height(8.dp))

            if(earningsToday.isEmpty()){
                NoDataPlaceholder()
            }else{
                // Iterate over earnings when not empty
                for (index in earningsToday.indices) {
                    val earn = earningsToday[index]


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
fun DailyEarnsScreenPreview() {
    DailyEarnsScreen(navController = rememberNavController(), "2026-06-02")
}


@Composable
fun MonthlyHeaderDaily(
    monthWord: String
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

            Text(
                text = monthWord,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.charcoal_blue)
            )
        }

    }
}





