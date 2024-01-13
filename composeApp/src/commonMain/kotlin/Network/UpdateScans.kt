package Network

import Data.Network
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.util.InternalAPI

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
