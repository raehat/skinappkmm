package Data

import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode

object Auth {
    const val URL = "http://192.168.1.40:3000"

    val client = HttpClient()

    fun isSuccessfulResponse(status: HttpStatusCode): Boolean {
        return status.value in 200..299
    }
}