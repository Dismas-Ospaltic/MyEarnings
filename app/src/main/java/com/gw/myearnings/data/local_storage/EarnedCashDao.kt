package com.gw.myearnings.data.local_storage


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gw.myearnings.model.EarnedCashEntity
import com.gw.myearnings.model.MonthlyEarningReport
import com.gw.myearnings.utils.dateFormat
import kotlinx.coroutines.flow.Flow

//This interface defines all the database operations.
@Dao
interface EarnedCashDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEarnings(earnings: EarnedCashEntity)

    @Update
    suspend fun updateEarnings(earnings: EarnedCashEntity)


    @Query("SELECT * FROM earned_cash WHERE date=:dateToday AND  status='active' ORDER BY timestamp DESC")
    fun getAllEarningsByDate(dateToday: String): Flow<List<EarnedCashEntity>>


    @Query("SELECT * FROM earned_cash WHERE  status='archived' ORDER BY timestamp DESC")
    fun getAllEarningsArchived(): Flow<List<EarnedCashEntity>>


    // 🔹 Delete an Earned item by unique ID
    @Query("DELETE FROM earned_cash WHERE itemNumber = :itemNumber")
    suspend fun deleteEarningsByItemNumber(itemNumber: String): Int

    @Query("""
        UPDATE earned_cash 
        SET status= :newStatus
        WHERE itemNumber = :itemNumber
    """)
    suspend fun updateEarnItemStatusById(
        newStatus: String,
        itemNumber: String
    ): Int?



    @Query("""
        UPDATE earned_cash 
        SET totalEarned = :totalEarned, 
            totalSaved = :totalSaved, 
            note= :note,
            totalSpend = :totalSpend,
            source =:source
        WHERE itemNumber = :itemNumber
    """)
    suspend fun updateEarnItemById(
        itemNumber: String,
        totalEarned: Float,
         totalSaved: Float,
       totalSpend: Float,
         source: String,
         note: String? = null
    ): Int?


    //counts and totals
    @Query("SELECT SUM(totalEarned) FROM earned_cash WHERE date LIKE :month || '%'")
    fun getMonthlyTotalEarnings(month: String): Flow<Float?>

    //counts and totals
    @Query("SELECT SUM(totalSaved) FROM earned_cash WHERE date LIKE :month || '%'")
    fun getMonthlyTotalSaved(month: String): Flow<Float?>

    //counts and totals
    @Query("SELECT SUM(totalSpend) FROM earned_cash WHERE date LIKE :month || '%'")
    fun getMonthlyTotalSpend(month: String): Flow<Float?>



    @Query("""
    SELECT 
        date,
        SUM(totalEarned) AS totalEarned,
        SUM(totalSpend) AS totalSpend,
        SUM(totalSaved) AS totalSaved
    FROM earned_cash
    GROUP BY date
    ORDER BY date DESC
""")
    fun getMonthlyEarningReports(): Flow<List<MonthlyEarningReport>>

}