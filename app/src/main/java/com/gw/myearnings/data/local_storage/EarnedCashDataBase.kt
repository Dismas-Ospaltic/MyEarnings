package com.gw.myearnings.data.local_storage


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gw.myearnings.model.EarnedCashEntity


@Database(entities = [EarnedCashEntity::class], version = 1, exportSchema = false)
abstract class EarnedCashDataBase : RoomDatabase() {

    abstract fun earnedCashDao(): EarnedCashDao



    companion object {
        @Volatile
        private var INSTANCE: EarnedCashDataBase? = null

        fun getDatabase(context: Context): EarnedCashDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EarnedCashDataBase::class.java,
                    "earnings_data"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}