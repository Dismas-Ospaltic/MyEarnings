package com.gw.myearnings.data.local_storage


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gw.myearnings.model.EarnedCashEntity
import com.gw.myearnings.utils.dateFormat
import kotlinx.coroutines.flow.Flow

//This interface defines all the database operations.
@Dao
interface EarnedCashDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEarnings(earnings: EarnedCashEntity)

    @Update
    suspend fun updateEarnings(earnings: EarnedCashEntity)


//    @Query("SELECT * FROM general_orders ORDER BY timestamp DESC")
//    fun getAllGenOrders(): Flow<List<GeneralOrdersEntity>>

    @Query("SELECT * FROM earned_cash WHERE date=:dateToday ORDER BY timestamp DESC")
    fun getAllEarningsByDate(dateToday: String): Flow<List<EarnedCashEntity>>


//    @Query("SELECT * FROM general_orders WHERE status = 'unpaid' ORDER BY timestamp DESC")
//    fun getAllGenOrdersUnpaid(): Flow<List<GeneralOrdersEntity>>
//
//
//    @Query("SELECT * FROM general_orders WHERE status = 'paid' ORDER BY timestamp DESC")
//    fun getAllGenOrdersPaid(): Flow<List<GeneralOrdersEntity>>
//
//    // 🔹 Delete an order by unique ID
//    @Query("DELETE FROM general_orders WHERE orderNumber = :orderNumber")
//    suspend fun deleteOrderByNumber(orderNumber: String): Int


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
            totalSpend = :totalSpend
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

    // 🔹 Count all orders
    @Query("SELECT COUNT(*) FROM general_orders")
    suspend fun getAllGenOrderCount(): Int

    @Query("SELECT COUNT(*) FROM general_orders WHERE status = 'unpaid' AND date =:date")
    fun getAllUnpaidOrderCount(date: String): Flow<Int?>

    @Query("SELECT COUNT(*) FROM general_orders WHERE status = 'paid' AND date =:date")
    fun getAllPaidOrderCount(date: String): Flow<Int?>

    @Query("SELECT SUM(totalOrder) FROM general_orders WHERE date=:date")
    fun getTodayTotalOrders(date: String): Flow<Float?>


    @Query("SELECT SUM(totalOrder) FROM general_orders")
    fun getTotalOrders(): Flow<Float?>

}