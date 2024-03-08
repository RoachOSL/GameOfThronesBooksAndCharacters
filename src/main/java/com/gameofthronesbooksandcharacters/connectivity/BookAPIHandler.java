package com.gameofthronesbooksandcharacters.connectivity;

import com.gameofthronesbooksandcharacters.datamodel.Book;
import com.gameofthronesbooksandcharacters.mappers.BookMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookAPIHandler {
    private final HttpClient client = HttpClient.newBuilder().build();
    private final String BOOKS_API_URL = "https://anapioficeandfire.com/api/books/";

    public Optional<Book> getSingleBook(int bookNumber) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BOOKS_API_URL + "/" + bookNumber))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return BookMapper.mapSingleBook(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Book> getAllBook() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BOOKS_API_URL + "?pageSize=50"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return BookMapper.mapAllBooks(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
