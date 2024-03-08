package com.gameofthronesbooksandcharacters.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofthronesbooksandcharacters.datamodel.StoryCharacter;
import com.gameofthronesbooksandcharacters.util.SharedObjectMapper;

import java.util.List;
import java.util.Optional;

public class StoryCharacterMapper {

    private static final ObjectMapper objectMapper = SharedObjectMapper.getObjectMapper();

    public static Optional<StoryCharacter> mapSingleCharacter(String characterJSON) {
        try {
            if (characterJSON.startsWith("[")) {
                List<StoryCharacter> characters = objectMapper.readValue(characterJSON, new TypeReference<List<StoryCharacter>>() {
                });
                return characters.isEmpty() ? Optional.empty() : Optional.of(characters.get(0));
            } else {
                StoryCharacter character = objectMapper.readValue(characterJSON, StoryCharacter.class);
                return Optional.ofNullable(character);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
