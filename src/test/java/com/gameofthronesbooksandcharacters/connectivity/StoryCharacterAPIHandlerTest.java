package com.gameofthronesbooksandcharacters.connectivity;

import org.junit.jupiter.api.Test;

class StoryCharacterAPIHandlerTest {

    @Test
    void getCharacterByIndex() {
    }

    @Test
    void getCharacterByName() {

        StoryCharacterAPIHandler storyCharacterAPIHandler = new StoryCharacterAPIHandler();

        System.out.println(storyCharacterAPIHandler.getCharacterByIndex(583));
        System.out.println(storyCharacterAPIHandler.getCharacterByName("Jon Snow"));

    }
}