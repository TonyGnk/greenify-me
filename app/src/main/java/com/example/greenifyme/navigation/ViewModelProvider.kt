package com.example.greenifyme.navigation


import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.greenifyme.ApplicationSetup
import com.example.greenifyme.ui.admin.home.model.AdminHomeModel
import com.example.greenifyme.ui.database_manager.DBManagerNavDestination
import com.example.greenifyme.ui.database_manager.account.AccountModel
import com.example.greenifyme.ui.database_manager.record.RecordModel

// Every viewmodel has to be initialized here with the corresponding repository
object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AccountModel(
                DBManagerNavDestination.Account,
                applicationSetup().accountRepositoryNew,
                applicationSetup().recordRepositoryNew
            )
        }
        initializer {
            RecordModel(
                DBManagerNavDestination.Record,
                applicationSetup().accountRepositoryNew,
                applicationSetup().recordRepositoryNew
            )
        }
        initializer {
            AdminHomeModel(
                applicationSetup().accountRepository,
                applicationSetup().recordRepository
            )
        }
    }

}


fun CreationExtras.applicationSetup(): ApplicationSetup =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ApplicationSetup)
