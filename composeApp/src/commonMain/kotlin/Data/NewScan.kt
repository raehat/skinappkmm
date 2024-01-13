package Data

import kotlinx.serialization.Serializable

@Serializable
data class NewScan (
    val email: String,
    val imageBase64: String,
    val analysisResult: String
)