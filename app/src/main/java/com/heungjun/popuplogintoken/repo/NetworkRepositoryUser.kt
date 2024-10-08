package com.heungjun.popuplogintoken.repo

import com.heungjun.popuplogintoken.model.ApiResponse
import com.heungjun.popuplogintoken.model.Login
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkRepositoryUser {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun signUpApi(signUpData: Map<String, Any>): ApiResponse {
        val response: ApiResponse = client.post("http://10.0.2.2:8080/auth/user/signup") {
            contentType(ContentType.Application.Json)
            setBody(signUpData)
        }.body()

        return response
    }

    // 기존 로그인 API 함수
    suspend fun loginApi(email: String, password: String): ApiResponse {
        val response: ApiResponse = client.post("http://10.0.2.2:8080/auth/user/login") {
            contentType(ContentType.Application.Json)
            setBody(Login(email, password))
        }.body()

        return response
    }
}