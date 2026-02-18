package com.yanmaia12.MyGameList.service;

import com.yanmaia12.MyGameList.Keys;
import com.yanmaia12.MyGameList.model.Jogo;
import org.apache.catalina.mapper.Mapper;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Igdb {
    public Jogo[] buscarJogo(String nomeDoJogo) throws IOException, InterruptedException {
        TwitchAuth twitchAuth = new TwitchAuth();
        String clientId = Keys.TWITCH_CLIENT_ID;
        String url = "https://api.igdb.com/v4/games";
        String pesquisa = "search \"" + nomeDoJogo + "\"; fields id, name, rating, category ; limit 10;";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Client-ID", clientId)
                .header("Authorization", "Bearer " + twitchAuth.auth())
                .POST(HttpRequest.BodyPublishers.ofString(pesquisa))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        Jogo[] jogo = objectMapper.readValue(response.body(), Jogo[].class);

        return jogo;
    }
}
