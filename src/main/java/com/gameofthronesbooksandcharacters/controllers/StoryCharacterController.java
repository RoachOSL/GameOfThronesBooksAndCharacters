package com.gameofthronesbooksandcharacters.controllers;

import com.gameofthronesbooksandcharacters.connectivity.StoryCharacterAPIHandler;
import com.gameofthronesbooksandcharacters.datamodel.StoryCharacter;
import com.gameofthronesbooksandcharacters.datamodel.StoryCharacterSearchObject;
import com.gameofthronesbooksandcharacters.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class StoryCharacterController {
    private final StoryCharacterAPIHandler storyCharacterAPIHandler;
    private final BookService bookService;

    @Autowired
    public StoryCharacterController(StoryCharacterAPIHandler storyCharacterAPIHandler, BookService bookService) {
        this.storyCharacterAPIHandler = storyCharacterAPIHandler;
        this.bookService = bookService;
    }

    @GetMapping("/storyCharacterSearch")
    public String getCharacterSearchView(Model model) {
        model.addAttribute("storyCharacterSearchObject", new StoryCharacterSearchObject());
        return "storyCharacterSearch";
    }

    @PostMapping("/storyCharacterSearch")
    public String getSpecificCharacter(Model model, StoryCharacterSearchObject storyCharacterSearchObject) {
        Optional<StoryCharacter> storyCharacterOpt = storyCharacterAPIHandler.getCharacterByName(storyCharacterSearchObject.getName());
        if (storyCharacterOpt.isPresent()) {
            StoryCharacter character = storyCharacterOpt.get();
            bookService.enrichCharacterWithBookTitles(character);
            model.addAttribute("storyCharacter", character);
            return "storyCharacter";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @GetMapping("/storyCharacter")
    public String getRandomCharacter(Model model) {
        Optional<StoryCharacter> storyCharacterOpt = storyCharacterAPIHandler.getCharacterByIndex(ThreadLocalRandom.current().nextInt(1, 1100 + 1));
        if (storyCharacterOpt.isPresent()) {
            StoryCharacter character = storyCharacterOpt.get();
            bookService.enrichCharacterWithBookTitles(character);
            model.addAttribute("storyCharacter", character);
            return "storyCharacter";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }
}
