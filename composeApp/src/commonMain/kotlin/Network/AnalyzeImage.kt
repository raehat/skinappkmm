package Network

import Data.Network
import Data.LesionImage
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class, InternalAPI::class)
suspend fun analyzeImage(image: ByteArray) : HttpResponse? {
    try {
        val encodedImage = Base64.encode(image)
        val response = Network.client.post("${Network.ML_URL}/predict") {
            body = Json.encodeToString(
                LesionImage(
                    image_data = encodedImage
                )
            )
            contentType(ContentType.Application.Json)
        }
        return response

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}