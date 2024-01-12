package Data

import kotlinx.serialization.Serializable

@Serializable
data class AnalysisResult(
    val `class`: String = "",
    val probs: List<List<String>> = listOf()
)