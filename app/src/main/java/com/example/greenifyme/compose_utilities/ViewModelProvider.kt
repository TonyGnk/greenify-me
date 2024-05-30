package com.example.greenifyme.compose_utilities


import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.greenifyme.ApplicationSetup
import com.example.greenifyme.ui.admin.home.AdminHomeModel
import com.example.greenifyme.ui.admin.home.charts.QuantityChartModel
import com.example.greenifyme.ui.admin.home.charts.RankChartModel
import com.example.greenifyme.ui.admin.level.AdminLevelModel
import com.example.greenifyme.ui.admin.materials.AddingMaterialsModel
import com.example.greenifyme.ui.admin.notifications.AdminNotificationsModel
import com.example.greenifyme.ui.admin.rank.AdminRankModel
import com.example.greenifyme.ui.database_manager.DBManagerNavDestination
import com.example.greenifyme.ui.database_manager.account.AccountModel
import com.example.greenifyme.ui.database_manager.material.MaterialModel
import com.example.greenifyme.ui.database_manager.record.RecordModel
import com.example.greenifyme.ui.database_manager.track.TrackModel
import com.example.greenifyme.ui.user.form.UserFormModel
import com.example.greenifyme.ui.user.home.UserHomeModel
import com.example.greenifyme.ui.user.home.citizen_points.CitizenPointsModel

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
            AdminLevelModel(
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
            AddingMaterialsModel(
                applicationSetup().greenRepository,
            )
        }
        initializer {
            AdminNotificationsModel(
                applicationSetup().greenRepository,
            )
        }
        initializer {
            CitizenPointsModel(
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