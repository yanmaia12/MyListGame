package com.yanmaia12.MyGameList.service;

import com.yanmaia12.MyGameList.Keys;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TwitchAuth {
    public String auth() throws IOException, InterruptedException {
        String clientId = Keys.TWITCH_CLIENT_ID;
        String clientSecret = Keys.TWITCH_CLIENT_SECRET;
        String url = "https://id.twitch.tv/oauth2/token?client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&grant_type=client_credentials";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(response.body());

        String token = jsonNode.get("access_token").asText();

        return token;
    }

}
