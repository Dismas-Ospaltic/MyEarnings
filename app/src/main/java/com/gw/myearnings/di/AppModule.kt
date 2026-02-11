package com.gw.myearnings.di




import com.gw.myearnings.data.local_storage.EarnedCashDataBase
import com.gw.myearnings.repository.EarnedCashRepository
import com.gw.myearnings.viewmodel.EarnedCashViewModel
import com.gw.myearnings.viewmodel.SettingsViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {
//  Define dependency injection here

    single { EarnedCashDataBase.getDatabase(get()).earnedCashDao() }
    single { EarnedCashRepository(get()) }
    viewModel{ EarnedCashViewModel(get())}


    viewModel{ SettingsViewModel(get()) }


}