package com.example.greenifyme.navigation


import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.greenifyme.ApplicationSetup

// Every viewmodel has to be initialized here with the corresponding repository
object ViewModelProvider {
    val Factory = viewModelFactory {
//        initializer {
//            HomeViewModel(
//                applicationSetup().accountRepository
//            )
//        }

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
