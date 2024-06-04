package com.example.greenifyme.compose_utilities


import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.greenifyme.ApplicationSetup
import com.example.greenifyme.data.Account
import com.example.greenifyme.ui.admin.home.AdminHomeModel
import com.example.greenifyme.ui.admin.materials.AdminEditMaterialsModel
import com.example.greenifyme.ui.admin.notifications.AdminNotificationsModel
import com.example.greenifyme.ui.admin.notifications.NotificationItem
import com.example.greenifyme.ui.database.manager.content.ManagerViewModel
import com.example.greenifyme.ui.database.panel.DatabasePanelModel
import com.example.greenifyme.ui.landing_page.LandingPageViewModel
import com.example.greenifyme.ui.user.form.UserFormModel
import com.example.greenifyme.ui.user.home.UserHomeModel


// Every viewmodel has to be initialized here with the corresponding repository
object SharedModelProvider {
    fun Factory(useSampleData: Boolean) = viewModelFactory {

        initializer {
            ManagerViewModel(
                if (useSampleData) applicationSetup().normalRepository else applicationSetup().sampleRepository,
            )
        }
        initializer {
            DatabasePanelModel(
                applicationSetup().sampleRepository,
                applicationSetup().normalRepository,
            )
        }
        initializer {
            LandingPageViewModel(
                applicationSetup().normalRepository,
                applicationSetup().sampleRepository,
            )
        }

        initializer {
            AdminHomeModel(
                if (useSampleData) applicationSetup().normalRepository else applicationSetup().sampleRepository,
            )
        }
        initializer {
            AdminEditMaterialsModel(
                if (useSampleData) applicationSetup().normalRepository else applicationSetup().sampleRepository,
            )
        }

    }
}

object SharedModelProviderWithAccount {
    fun Factory(
        useSampleData: Boolean, account: Account
    ) = viewModelFactory {
        initializer {
            UserFormModel(
                if (useSampleData) applicationSetup().normalRepository else applicationSetup().sampleRepository,
                account,
                useSampleData
            )
        }
        initializer {
            UserHomeModel(
                if (useSampleData) applicationSetup().normalRepository else applicationSetup().sampleRepository,
                account
            )
        }
    }
}

object SharedModelProviderWithNotification {
    fun Factory(
        useSampleData: Boolean, formNotification: NotificationItem.FormNotification?
    ) = viewModelFactory {
        initializer {
            AdminNotificationsModel(
                if (useSampleData) applicationSetup().normalRepository else applicationSetup().sampleRepository,
                formNotification
            )
        }
    }
}

fun CreationExtras.applicationSetup(): ApplicationSetup =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ApplicationSetup)