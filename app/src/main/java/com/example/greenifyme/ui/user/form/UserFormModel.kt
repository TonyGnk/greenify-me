package com.example.greenifyme.ui.user.form

import androidx.lifecycle.ViewModel
import com.example.greenifyme.R
import com.example.greenifyme.data.GreenRepository

class UserFormModel(repository: GreenRepository) : ViewModel() {

}


data class UserFormState(
    val name: String = "",
)

//data class UserFormStrings(
//    val newRecord = R.string.user_form_new_record,
//)