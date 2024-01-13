@file:OptIn(InternalAPI::class)

package Network

import Data.Network.URL
import Data.Network.client
import Data.Network.isSuccessfulResponse
import Data.UpdateForgotPassword
import Data.VerifiedUser
import Data.VerifyOTP
import Data.VerifyOTPForgotPassword
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

suspend fun signUp(email: String, password: String) : Boolean {
    try {
        val response = client.post("$URL/signup") {
            body = Json.encodeToString(
                VerifiedUser(
                    email = email,
                    password = password
                )
            )
            contentType(ContentType.Application.Json)
        }
        return isSuccessfulResponse(response.status)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}

suspend fun verifyOTP(email: String, otp: String) : Boolean {
    try {
        val response = client.post("$URL/verify_otp") {
            body = Json.encodeToString(
                VerifyOTP(email = email, otp = otp)
            )
            contentType(ContentType.Application.Json)
        }
        return isSuccessfulResponse(response.status)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}

suspend fun login(email: String, password: String) : Boolean {
    try {
        val response = client.post("$URL/login") {
            body = Json.encodeToString(
                VerifiedUser(
                    email = email,
                    password = password
                )
            )
            contentType(ContentType.Application.Json)
        }
        return isSuccessfulResponse(response.status)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}

suspend fun sendOTPForgotPassword(email: String) : Boolean {
    try {
        val response = client.post("$URL/forgot_password") {
            body = Json.encodeToString(
                VerifyOTPForgotPassword(
                    email = email
                )
            )
            contentType(ContentType.Application.Json)
        }
        return isSuccessfulResponse(response.status)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}

suspend fun checkOTPForgotPassword(email: String, otp: String) : Boolean {
    try {
        val response = client.post("$URL/verify_otp_forgot_password") {
            body = Json.encodeToString(
                VerifyOTP(
                    email = email,
                    otp = otp
                )
            )
            contentType(ContentType.Application.Json)
        }
        return isSuccessfulResponse(response.status)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}

suspend fun updateForgotPassword(email: String, password: String, otp: String) : Boolean {
    try {
        val response = client.post("$URL/update_password") {
            body = Json.encodeToString(
                UpdateForgotPassword(
                    email = email,
                    password = password,
                    otp = otp
                )
            )
            contentType(ContentType.Application.Json)
        }
        return isSuccessfulResponse(response.status)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}