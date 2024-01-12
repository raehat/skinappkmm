package Data

import kotlinx.serialization.Serializable
import kotlin.io.encoding.Base64

@Serializable
data class LesionImage (
    val image_data : String
)