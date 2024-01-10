package Data

import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode

object Auth {
    const val URL = "https://skinappkmmbackend.vercel.app"

    val client = HttpClient()

    fun isSuccessfulResponse(status: HttpStatusCode): Boolean {
        return status.value in 200..299
    }
}