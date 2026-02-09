package com.gw.myearnings.app_screens.ui_components

import com.gw.myearnings.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.CreditCard
import compose.icons.fontawesomeicons.solid.PiggyBank
import compose.icons.fontawesomeicons.solid.Wallet
import org.koin.androidx.compose.koinViewModel

@Composable
fun SumCountDisplay() {

    val data = listOf(
        Triple("Total Income", "Kes. 100,000", FontAwesomeIcons.Solid.Wallet),
        Triple("Total Saved", "Kes. 80,000", FontAwesomeIcons.Solid.PiggyBank),
        Triple("Total Spend", "Kes. 20,000", FontAwesomeIcons.Solid.CreditCard)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.charcoal_blue),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),

    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

            data.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    rowItems.forEach { stat ->
                        DataItem(
                            title = stat.first,
                            value = stat.second,
                            icon = stat.third,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}



@Composable
fun DataItem(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = colorResource(id = R.color.pale_oak),
                shape = MaterialTheme.shapes.medium
            )
            .border(
                1.dp,
                color = colorResource(id = R.color.lilac_ash),
                shape = MaterialTheme.shapes.medium
            )
            .padding(14.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = colorResource(id = R.color.dusk_blue),
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = title,
                fontSize = 13.sp,
                color = colorResource(id = R.color.charcoal_blue)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = value,
            fontSize = 20.sp,
            color = colorResource(id = R.color.charcoal_blue)
        )
    }
}
