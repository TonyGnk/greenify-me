package com.example.greenifyme.data.account

import java.security.MessageDigest

// This function hashes the password using SHA-256
// Takes a given password and returns a hashed version of it (String to String)
// Currently, is almost impossible to reverse the hash
fun hashPassword(password: String): String {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    val bytes = messageDigest.digest(password.toByteArray())
    return bytes.fold("") { acc, byte -> acc + "%02x".format(byte) }
}

// For API levels below 28 (optional) - Keep it, might be useful in the future
//fun hashPasswordPBKDF2(password: String): String {
//    val salt = ByteArray(32) // Generate a random salt for each password
//    val random = SecureRandom()
//    random.nextBytes(salt)
//
//    val spec = PBKDF2KeySpec(password.toCharArray(), salt, 10000, 256) // Adjust iterations and key length as needed
//    val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
//    val key = factory.generateSecret(spec)
//    val bytes = key.encoded
//    return bytes.fold("") { acc, byte -> acc + "%02x".format(byte) } // Convert to hex string for storage
//}
