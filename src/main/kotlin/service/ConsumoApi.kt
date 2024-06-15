package service

import com.google.gson.Gson
import modelo.InfoJogo
import modelo.Jogo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumoApi {
    fun buscaJogo(id: String): Jogo {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()
        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        val json = response.body()
        val gson = Gson()
        val jsonJogo = gson.fromJson(json, InfoJogo::class.java)
        return Jogo(jsonJogo.info.title, jsonJogo.info.thumb)
    }
}