package Network

import Data.AnalysisResult
import Data.Auth
import Data.NewScan
import Data.VerifiedUser
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(InternalAPI::class)
suspend fun addNewScan(
    email: String,
    imageBase64: String,
    analysisResult: String
) : Boolean {
    try {
        val jsonBody = Json.encodeToString(
            NewScan(
                email,
                imageBase64,
                analysisResult
            )
        )
        val response = Auth.client.post("${Auth.URL}/new_scan_added") {
            body = jsonBody
            contentType(ContentType.Application.Json)
        }
        return Auth.isSuccessfulResponse(response.status)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}
