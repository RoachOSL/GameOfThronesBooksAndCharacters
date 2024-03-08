package com.gameofthronesbooksandcharacters.connectivity;

import com.gameofthronesbooksandcharacters.datamodel.StoryCharacter;
import com.gameofthronesbooksandcharacters.mappers.StoryCharacterMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class StoryCharacterAPIHandler {

    private final HttpClient client = HttpClient.newBuilder().build();
    private final String CHARACTERS_URL = "https://www.anapioficeandfire.com/api/characters";

    public Optional<StoryCharacter> getCharacterByIndex(int indexOfCharacter) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CHARACTERS_URL + "/" + indexOfCharacter))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return StoryCharacterMapper.mapSingleCharacter(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<StoryCharacter> getCharacterByName(String name) {
        try {
            name = name.trim().replaceAll(" ", "%20");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CHARACTERS_URL + "?name=" + name))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return StoryCharacterMapper.mapSingleCharacter(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
