package Network

import Data.Auth.URL
import Data.VerifiedUser
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(InternalAPI::class)
fun signUp(email: String, password: String) = CoroutineScope(Dispatchers.IO).launch {

    try {
        val response = client().post(URL) {
            body = Json.encodeToString(
                VerifiedUser(
                    email = email,
                    password = password)
            )
            contentType(ContentType.Application.Json)
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun client() = HttpClient()