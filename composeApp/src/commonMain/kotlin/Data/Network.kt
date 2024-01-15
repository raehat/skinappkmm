package Data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.http.HttpStatusCode

object Network {
    const val AUTH_URL = "https://skinappkmmbackend.vercel.app"
    const val ML_URL = "https://flask-production-9f74.up.railway.app"

    val client = HttpClient() {
        install(HttpTimeout) {
            requestTimeoutMillis = 60000
            connectTimeoutMillis = 60000
            socketTimeoutMillis = 60000
        }
    }

    fun isSuccessfulResponse(status: HttpStatusCode): Boolean {
        return status.value in 200..299
    }
}