package com.gameofthronesbooksandcharacters.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofthronesbooksandcharacters.datamodel.Book;
import com.gameofthronesbooksandcharacters.util.SharedObjectMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookMapper {
    private static final ObjectMapper objectMapper = SharedObjectMapper.getObjectMapper();

    public static Optional<Book> mapSingleBook(String bookJSON) {
        try {
            return Optional.ofNullable(objectMapper.readValue(bookJSON, Book.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static List<Book> mapAllBooks(String bookListJSON) {
        try {
            return objectMapper.readValue(bookListJSON, new TypeReference<List<Book>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
