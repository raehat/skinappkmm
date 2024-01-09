package Data

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOTP(
    val email : String,
    val otp: String
)

@Serializable
data class VerifyOTPForgotPassword(
    val email: String
)

@Serializable
data class UpdateForgotPassword(
    val email: String,
    val password: String,
    val otp: String
)