package Data

import kotlinx.serialization.Serializable

@Serializable
data class Scan(
    val imageBase64: String,
    val analysisResult: String,
    val date: String
)

@Serializable
data class Scans(
    val scans: List<Scan> = listOf()
)