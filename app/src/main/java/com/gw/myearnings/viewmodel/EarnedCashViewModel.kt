package com.gw.myearnings.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gw.myearnings.model.EarnedCashEntity
import com.gw.myearnings.model.MonthlyEarningReport
import com.gw.myearnings.repository.EarnedCashRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * ViewModel
 * - Exposes data to the UI
 * - Handles background work using viewModelScope
 */
class EarnedCashViewModel(
    private val repository: EarnedCashRepository
) : ViewModel() {

    /* -------------------- INSERT & UPDATE -------------------- */

    // Insert a new earning item
    fun insertEarnings(earnings: EarnedCashEntity) {
        viewModelScope.launch {
            repository.insertEarnings(earnings)
        }
    }

    // Update an existing earning item
    fun updateEarnings(earnings: EarnedCashEntity) {
        viewModelScope.launch {
            repository.updateEarnings(earnings)
        }
    }

    /* -------------------- READ -------------------- */

    // Observe active earnings for a specific date
    fun getEarningsByDate(dateToday: String): Flow<List<EarnedCashEntity>> {
        return repository.getAllEarningsByDate(dateToday)
    }

    fun getEarningsByYearMonth(month: String): Flow<List<EarnedCashEntity>> {
        return repository.getEarningsByYearMonth(month)
    }

    // Observe archived earnings
    fun getArchivedEarnings(): Flow<List<EarnedCashEntity>> {
        return repository.getAllEarningsArchived()
    }

    /* -------------------- DELETE -------------------- */

    // Delete earning by item number
    fun deleteEarning(itemNumber: String) {
        viewModelScope.launch {
            repository.deleteEarningsByItemNumber(itemNumber)
        }
    }

    /* -------------------- UPDATE FIELDS -------------------- */

    // Update earning item status
    fun updateEarningStatus(
        newStatus: String,
        itemNumber: String
    ) {
        viewModelScope.launch {
            repository.updateEarnItemStatus(newStatus, itemNumber)
        }
    }

    // Update earning values
    fun updateEarningItem(
        itemNumber: String,
        totalEarned: Float,
        totalSaved: Float,
        totalSpend: Float,
        source: String,
        note: String?
    ) {
        viewModelScope.launch {
            repository.updateEarnItem(
                itemNumber,
                totalEarned,
                totalSaved,
                totalSpend,
                source,
                note
            )
        }
    }

    /* -------------------- MONTHLY TOTALS -------------------- */

    fun getMonthlyTotalEarnings(month: String): Flow<Float?> {
        return repository.getMonthlyTotalEarnings(month)
    }

    fun getMonthlyTotalSaved(month: String): Flow<Float?> {
        return repository.getMonthlyTotalSaved(month)
    }

    fun getMonthlyTotalSpend(month: String): Flow<Float?> {
        return repository.getMonthlyTotalSpend(month)
    }

    /* -------------------- REPORTS -------------------- */

    fun getMonthlyReports(): Flow<List<MonthlyEarningReport>> {
        return repository.getMonthlyEarningReports()
    }
}
