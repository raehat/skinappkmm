package Data

import kotlinx.serialization.Serializable
@Serializable
data class VerifiedUser(
    val email : String,
    val password : String
)