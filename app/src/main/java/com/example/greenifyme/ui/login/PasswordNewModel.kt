package com.example.greenifyme.ui.login

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.ApplicationSetup
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.account.hashPassword
import com.example.greenifyme.data.account.toBundle
import com.example.greenifyme.ui.user.UserHomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PasswordModel(application: Application) : AndroidViewModel(application) {
    private val repository: GreenRepository

    private val _passwordState = MutableLiveData(PasswordState())
    val passwordState: LiveData<PasswordState> get() = _passwordState

    private val _loginState = MutableLiveData(LoginState())
    val loginState: LiveData<LoginState> get() = _loginState

    private val _registerState = MutableLiveData(RegisterState())
    val registerState: LiveData<RegisterState> get() = _registerState

    private var account: Account? = null

    init {
        val app = application as ApplicationSetup
        repository = app.greenRepository
    }

    fun updatePassword(password: String) {
        _passwordState.value = _passwordState.value?.copy(password = password)
    }

    fun updateEmail(email: String) {
        _loginState.value = _loginState.value?.copy(email = email)
        _registerState.value = _registerState.value?.copy(email = email)
    }

    fun updateRegisterState(
        name: String, email: String, password: String, confirmPassword: String
    ) {
        _registerState.value = _registerState.value?.copy(
            name = name,
            email = email,
            password = password,
            confirmPassword = confirmPassword
        )
    }

    fun onSignInPressed() {
        viewModelScope.launch {
            val password = _passwordState.value?.password

            if (account != null && account!!.password == hashPassword(password ?: "")) {
                _passwordState.value = _passwordState.value?.copy(isSignedIn = true)
            } else {
                _passwordState.value = _passwordState.value?.copy(isPasswordError = true)
            }
        }
    }

    fun onNextPressed() {
        viewModelScope.launch {
            val email = _loginState.value?.email ?: ""

            if (email.isEmpty()) {
                _loginState.value = _loginState.value?.copy(type = EmailType.EMPTY)
                return@launch
            }

            if (!email.isEmailFormat()) {
                _loginState.value = _loginState.value?.copy(type = EmailType.WRONG_FORMAT)
                return@launch
            }

            val accountWithEmail = withContext(Dispatchers.IO) {
                repository.getAccount(email)
            }

            if (accountWithEmail == null) {
                _loginState.value = _loginState.value?.copy(type = EmailType.NOT_REGISTERED)
            } else {
                account = accountWithEmail
                _loginState.value = _loginState.value?.copy(type = EmailType.SUCCESS)
                _passwordState.value = _passwordState.value?.copy(email = email)
            }
        }
    }

    fun onRegisterPressed() {
        viewModelScope.launch {
            val name = _registerState.value?.name ?: ""
            val email = _registerState.value?.email ?: ""
            val password = _registerState.value?.password ?: ""
            val confirmPassword = _registerState.value?.confirmPassword ?: ""

            if (name.isEmpty()) {
                _registerState.value = _registerState.value?.copy(type = RegisterResult.NAME_EMPTY)
                return@launch
            }

            if (email.isEmpty() || !email.isEmailFormat()) {
                _registerState.value =
                    _registerState.value?.copy(type = RegisterResult.EMAIL_NOT_VALID_OR_EMPTY)
                return@launch
            }

            if (password.isEmpty()) {
                _registerState.value =
                    _registerState.value?.copy(type = RegisterResult.PASSWORD_EMPTY)
                return@launch
            }

            if (confirmPassword.isEmpty()) {
                _registerState.value =
                    _registerState.value?.copy(type = RegisterResult.PASSWORD_CONFIRM_EMPTY)
                return@launch
            }

            if (password != confirmPassword) {
                _registerState.value =
                    _registerState.value?.copy(type = RegisterResult.PASSWORDS_NOT_MATCHING)
                return@launch
            }

            val accountWithEmail = withContext(Dispatchers.IO) {
                repository.getAccount(email)
            }

            if (accountWithEmail != null) {
                _registerState.value = _registerState.value?.copy(type = RegisterResult.EMAIL_EXIST)
                return@launch
            }

            account = Account(
                name = name,
                email = email,
                password = hashPassword(password)
            )
            _registerState.value = _registerState.value?.copy(type = RegisterResult.SUCCESS)
            navigateToUserHome(View(getApplication()))
        }
    }

    fun navigateToUserHome(view: View) {
        val intent = Intent(view.context, UserHomeActivity::class.java).apply {
            putExtra("AccountIdToLoginIn", account?.toBundle())
        }
        view.context.startActivity(intent)
        (view.context as Activity).finish()
    }

    private fun String.isEmpty(): Boolean = TextUtils.isEmpty(this)

    private fun String.isEmailFormat(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

data class PasswordState(
    val password: String = "",
    val isSignedIn: Boolean = false,
    val customText: String = "",
    val email: String = "",
    val isPasswordError: Boolean = false,
)

data class LoginState(
    val email: String = "",
    val type: EmailType? = null,
)

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val type: RegisterResult = RegisterResult.NULL
)

enum class EmailType { SUCCESS, NOT_REGISTERED, EMPTY, WRONG_FORMAT }

enum class RegisterResult {
    NULL, SUCCESS, EMAIL_EXIST, EMAIL_NOT_VALID_OR_EMPTY, NAME_EMPTY, PASSWORD_EMPTY, PASSWORD_CONFIRM_EMPTY, PASSWORDS_NOT_MATCHING
}