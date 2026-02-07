package com.gw.myearnings.model



import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gw.myearnings.utils.dateFormat


@Entity(tableName = "earned_cash")
data class EarnedCashEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String = dateFormat(System.currentTimeMillis()), //DD-MM-YYYY
    val totalEarned: Float,
    val totalSaved: Float,
    val totalSpend: Float,
    val source: String,
    val note: String?=null,
    val status: String = "active",
    val itemNumber: String,
    val timestamp: Long = System.currentTimeMillis()
)