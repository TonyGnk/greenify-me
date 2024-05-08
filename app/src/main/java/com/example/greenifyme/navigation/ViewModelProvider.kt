package com.example.greenifyme.navigation


import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.greenifyme.ApplicationSetup
import com.example.greenifyme.ui.database_manager.DBManagerNavDestination
import com.example.greenifyme.ui.database_manager.account.AccountModel
import com.example.greenifyme.ui.database_manager.record.RecordModel

// Every viewmodel has to be initialized here with the corresponding repository
object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AccountModel(
                DBManagerNavDestination.Account,
                applicationSetup().accountRepository,
                applicationSetup().recordRepository
            )
        }
        initializer {
            RecordModel(
                DBManagerNavDestination.Record,
                applicationSetup().accountRepository,
                applicationSetup().recordRepository
            )
        }

        // Add more viewModels here
        // initializer {
        //     AnotherViewModel(
        //          applicationSetup().anotherRepository
        //     )
        // }
    }

}


fun CreationExtras.applicationSetup(): ApplicationSetup =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ApplicationSetup)
