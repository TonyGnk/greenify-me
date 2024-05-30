package com.example.greenifyme.ui.login

interface LoginResultListener {
    fun onLoginResult(result : LoginResult?)
}

interface RegisterResultListener {
    fun onRegisterResult(result : RegisterResult?)
}

interface PasswordResultListener {
    fun onPasswordResult(result : Boolean?)
}

enum class RegisterResult {
    SUCCESS, EMAIL_EXIST, EMAIL_NOT_VALID_OR_EMPTY, NAME_EMPTY, PASSWORD_EMPTY, PASSWORD_CONFIRM_EMPTY, PASSWORDS_NOT_MATCHING
}


enum class LoginResult {
    SUCCESS, EMAIL_NOT_REGISTERED, EMAIL_NOT_VALID_OR_EMPTY
}