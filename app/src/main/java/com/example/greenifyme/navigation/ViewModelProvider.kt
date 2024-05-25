package com.example.greenifyme.navigation


import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.greenifyme.ApplicationSetup
import com.example.greenifyme.ui.admin.home.charts.QuantityChartModel
import com.example.greenifyme.ui.admin.home.charts.RankChartModel
import com.example.greenifyme.ui.admin.home.model.AdminHomeModel
import com.example.greenifyme.ui.admin.rank.AdminRankModel
import com.example.greenifyme.ui.database_manager.DBManagerNavDestination
import com.example.greenifyme.ui.database_manager.account.AccountModel
import com.example.greenifyme.ui.database_manager.material.MaterialModel
import com.example.greenifyme.ui.database_manager.record.RecordModel
import com.example.greenifyme.ui.database_manager.track.TrackModel
import com.example.greenifyme.ui.user.form.UserFormModel
import com.example.greenifyme.ui.user.home.UserHomeModel

// Every viewmodel has to be initialized here with the corresponding repository
object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AccountModel(
                DBManagerNavDestination.Account,
                applicationSetup().greenRepository,
            )
        }
        initializer {
            RecordModel(
                DBManagerNavDestination.Record,
                applicationSetup().greenRepository,
            )
        }
        initializer {
            TrackModel(
                DBManagerNavDestination.Track,
                applicationSetup().greenRepository,
            )
        }
        initializer {
            MaterialModel(
                DBManagerNavDestination.Material,
                applicationSetup().greenRepository,
            )
        }
        initializer {
            UserHomeModel(
                applicationSetup().greenRepository,
            )
        }
        initializer {
            AdminRankModel(
                applicationSetup().greenRepository,
            )
        }

        initializer {
            AdminHomeModel(
                applicationSetup().greenRepository,
            )
        }

        initializer {
            QuantityChartModel(
                applicationSetup().greenRepository,
            )
        }
        initializer {
            RankChartModel(
                applicationSetup().greenRepository,
            )
        }

        initializer {
            UserFormModel(
                applicationSetup().greenRepository,
            )
        }
    }
}


fun CreationExtras.applicationSetup(): ApplicationSetup =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ApplicationSetup)
