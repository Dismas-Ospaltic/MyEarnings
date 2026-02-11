package com.gw.myearnings.repository


import com.gw.myearnings.data.local_storage.EarnedCashDao
import com.gw.myearnings.model.AppConfigEntity
import com.gw.myearnings.model.EarnedCashEntity
import com.gw.myearnings.model.MonthlyEarningReport
import kotlinx.coroutines.flow.Flow

/**
 * Repository layer
 * - Acts as a bridge between DAO and ViewModel
 * - Keeps database logic out of the ViewModel
 */
class EarnedCashRepository(
    private val earnedCashDao: EarnedCashDao
) {

    /* -------------------- INSERT & UPDATE -------------------- */

    // Insert a new earning record
    suspend fun insertEarnings(earnings: EarnedCashEntity) {
        earnedCashDao.insertEarnings(earnings)
    }

    // Update an existing earning record
    suspend fun updateEarnings(earnings: EarnedCashEntity) {
        earnedCashDao.updateEarnings(earnings)
    }

    /* -------------------- READ OPERATIONS -------------------- */

    // Get all active earnings for a specific date
    fun getAllEarningsByDate(dateToday: String): Flow<List<EarnedCashEntity>> {
        return earnedCashDao.getAllEarningsByDate(dateToday)
    }

    // Get all archived earnings
    fun getAllEarningsArchived(): Flow<List<EarnedCashEntity>> {
        return earnedCashDao.getAllEarningsArchived()
    }

    /* -------------------- DELETE -------------------- */

    // Delete an earning item using its unique item number
    suspend fun deleteEarningsByItemNumber(itemNumber: String): Int {
        return earnedCashDao.deleteEarningsByItemNumber(itemNumber)
    }

    /* -------------------- UPDATE SPECIFIC FIELDS -------------------- */

    // Update the status of an earning item (active / archived)
    suspend fun updateEarnItemStatus(
        newStatus: String,
        itemNumber: String
    ): Int? {
        return earnedCashDao.updateEarnItemStatusById(newStatus, itemNumber)
    }

    // Update financial values of an earning item
    suspend fun updateEarnItem(
        itemNumber: String,
        totalEarned: Float,
        totalSaved: Float,
        totalSpend: Float,
        source: String,
        note: String?
    ): Int? {
        return earnedCashDao.updateEarnItemById(
            itemNumber = itemNumber,
            totalEarned = totalEarned,
            totalSaved = totalSaved,
            totalSpend = totalSpend,
            source = source,
            note = note
        )
    }

    /* -------------------- MONTHLY TOTALS -------------------- */

    // Total earnings for a given month (YYYY-MM)
    fun getMonthlyTotalEarnings(month: String): Flow<Float?> {
        return earnedCashDao.getMonthlyTotalEarnings(month)
    }

    // Total savings for a given month
    fun getMonthlyTotalSaved(month: String): Flow<Float?> {
        return earnedCashDao.getMonthlyTotalSaved(month)
    }

    // Total spending for a given month
    fun getMonthlyTotalSpend(month: String): Flow<Float?> {
        return earnedCashDao.getMonthlyTotalSpend(month)
    }

    /* -------------------- REPORTS -------------------- */

    // Daily grouped earning reports
    fun getMonthlyEarningReports(): Flow<List<MonthlyEarningReport>> {
        return earnedCashDao.getMonthlyEarningReports()
    }




    //settings
    suspend fun saveCurrency(currency: String) {
        earnedCashDao.saveCurrency(
            AppConfigEntity(
                id = 1,
                currency = currency
            )
        )
    }

    suspend fun getCurrency(): String {
        return earnedCashDao.getCurrency() ?: "KES"
    }
}
