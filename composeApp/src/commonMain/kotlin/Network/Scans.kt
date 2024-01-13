package Network

import Data.Network
import Data.Scans
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json

@OptIn(InternalAPI::class)
suspend fun addNewScan(
    email: String,
    image: ByteArray,
    analysisResult: String
) : Boolean {
    try {
        val formData = MultiPartFormDataContent(
            formData {
                append("email", email)
                append("analysisResult", analysisResult)

                append(
                    "image",
                    image,
                    Headers.build {
                        append(HttpHeaders.ContentType, "files/*")
                        append(HttpHeaders.ContentDisposition, "filename=image.jpeg")
                    }
                )
            }
        )

        val response = Network.client.post("${Network.URL}/new_scan_added") {
            body = formData
        }
        return Network.isSuccessfulResponse(response.status)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}

suspend fun getAllScans(email: String) : Scans? {
    try {

        val response = Network.client.get("${Network.URL}/get_all_scans?email=$email")
        return Json.decodeFromString<Scans>(response.bodyAsText())

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}
