package Network

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

@OptIn(InternalAPI::class)
fun signUp(email: String, password: String) = CoroutineScope(Dispatchers.IO).launch {
    val url = "http://localhost:3000/signup"

    val client = HttpClient()

    try {
        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            body = mapOf(
                "email" to email,
                "password" to password
            )
        }

        // Handle the response
        println("bruhh: $response")
    } catch (e: Exception) {
        // Handle the exception
        e.printStackTrace()
    } finally {
        client.close()
    }
}