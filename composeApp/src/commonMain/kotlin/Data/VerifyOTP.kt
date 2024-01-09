package Data

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOTP(
    val email : String,
    val otp: String
)

data class VerifyOTPForgotPassword(
    val email: String
)

data class UpdateForgotPassword(
    val email: String,
    val password: String,
    val otp: String
)