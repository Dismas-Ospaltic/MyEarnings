package com.gw.myearnings.model


data class MonthlyEarningReport(
    val month: String, // "2026-02"
    val date: String,
    val totalSaved: Float,
    val totalEarned: Float,
    val totalSpend: Float
)