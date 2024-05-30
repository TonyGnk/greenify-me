package com.example.greenifyme.ui.login

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import com.example.greenifyme.ApplicationSetup
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.account.hashPassword
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RegisterViewModel(application : Application) : AndroidViewModel(application) {
    private val repository : GreenRepository
    private val state = MutableStateFlow(LoginRegisterState())

    init {
        val app = application as ApplicationSetup
        repository = app.greenRepository
    }

    fun setNavigationAfter3Seconds(view : View) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Thread.sleep(1200)
                findNavController(view).popBackStack()
            }
        }

    }

    private fun nameEmpty() : Boolean = TextUtils.isEmpty(state.value.name)

    private fun emailNotValid() : Boolean =
        !Patterns.EMAIL_ADDRESS.matcher(state.value.email).matches()

    private fun emailEmpty() : Boolean = TextUtils.isEmpty(state.value.email)

    private fun passwordEmpty() : Boolean = TextUtils.isEmpty(state.value.password)

    private fun confirmPasswordEmpty() : Boolean = TextUtils.isEmpty(state.value.confirmPassword)

    private fun passwordNotMatch() : Boolean = state.value.password != state.value.confirmPassword

    fun sendFieldsToModel(
        name : String = "",
        email : String = "",
        password : String = "",
        confirmPassword : String = ""
    ) {
        state.update {
            it.copy(
                name = name, email = email, password = password, confirmPassword = confirmPassword
            )
        }
    }

    private suspend fun emailExists() : Boolean {
        return withContext(Dispatchers.IO) {
            repository.accountExists(state.value.email)
        }
    }

    private suspend fun isCredentialsCorrect() : Boolean {
        return withContext(Dispatchers.IO) {
            repository.accountExists(state.value.email, state.value.password)
        }
    }

    fun sendEmailFieldToModel(email : String) {
        state.update { it.copy(email = email) }
    }

    fun sendPasswordFieldToModel(password : String) {
        state.update { it.copy(password = password) }
    }

    fun onRegisterPressed(listener : RegisterResultListener) {
        viewModelScope.launch {
            if (nameEmpty()) {
                listener.onRegisterResult(RegisterResult.NAME_EMPTY)
                return@launch
            }

            if (emailNotValid() && emailEmpty()) {
                listener.onRegisterResult(RegisterResult.EMAIL_NOT_VALID_OR_EMPTY)
                return@launch
            }

            if (emailExists()) {
                listener.onRegisterResult(RegisterResult.EMAIL_EXIST)
                return@launch
            }

            if (passwordEmpty()) {
                listener.onRegisterResult(RegisterResult.PASSWORD_EMPTY)
                return@launch
            }

            if (confirmPasswordEmpty()) {
                listener.onRegisterResult(RegisterResult.PASSWORD_CONFIRM_EMPTY)
                return@launch
            }

            if (passwordNotMatch()) {
                listener.onRegisterResult(RegisterResult.PASSWORDS_NOT_MATCHING)
                return@launch
            }

            val account = Account(
                name = state.value.name,
                email = state.value.email,
                password = hashPassword(state.value.password)
            )
            repository.insert(account, viewModelScope)
            listener.onRegisterResult(RegisterResult.SUCCESS)
        }
    }

    fun onLoginPressed(listener : LoginResultListener) {
        viewModelScope.launch {
            if (emailEmpty() && emailNotValid()) {
                listener.onLoginResult(LoginResult.EMAIL_NOT_VALID_OR_EMPTY)
                return@launch
            }
            if (!emailExists()) {
                listener.onLoginResult(LoginResult.EMAIL_NOT_REGISTERED)
                return@launch
            }
            listener.onLoginResult(LoginResult.SUCCESS)
        }
    }

    fun onSignInPressed(listener : PasswordResultListener) {
        viewModelScope.launch {
            if (!isCredentialsCorrect()) {
                listener.onPasswordResult(false)
                return@launch
            }
            listener.onPasswordResult(true)
        }
    }
}

data class LoginRegisterState(
    val name : String = "",
    val email : String = "",
    val password : String = "",
    val confirmPassword : String = ""
)

private var emailField : String = ""
fun setEmailField(email : String) {
    emailField = email
}

fun getEmailField() : String = emailField