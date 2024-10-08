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

class NetworkRepositoryCompany {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun loginApi(email: String, password: String): ApiResponse {
        val response: ApiResponse = client.post("http://10.0.2.2:8080/auth/company/login") {
            contentType(ContentType.Application.Json)
            setBody(Login(email, password))
        }.body()

        return response
    }

    suspend fun signUpApi(
        email: String,
        password: String,
        companyName: String,
        companyId: String,
        managerName: String,
        address: String,
        detailAddress: String,
        postCode: String
    ): ApiResponse {
        val response: ApiResponse = client.post("http://10.0.2.2:8080/auth/company/signup") {
            contentType(ContentType.Application.Json)
            setBody(
                mapOf(
                    "email" to email,
                    "password" to password,
                    "companyName" to companyName,
                    "companyId" to companyId,
                    "managerName" to managerName,
                    "address" to address,
                    "detailAddress" to detailAddress,
                    "postCode" to postCode
                )
            )
        }.body()

        return response
    }
}