package com.gw.myearnings.model


data class MonthlyEarningReport(
    val date: String,
    val totalSaved: Float,
    val totalEarned: Float,
    val totalSpend: Float
)