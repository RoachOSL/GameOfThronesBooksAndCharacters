package com.gameofthronesbooksandcharacters.services;

import com.gameofthronesbooksandcharacters.connectivity.BookAPIHandler;
import com.gameofthronesbooksandcharacters.datamodel.Book;
import com.gameofthronesbooksandcharacters.datamodel.StoryCharacter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookAPIHandler bookAPIHandler;
    public BookService(BookAPIHandler bookAPIHandler) {
        this.bookAPIHandler = bookAPIHandler;
    }

    public Optional<String> getBookTitleByNumber(int bookNumber) {
        Optional<Book> bookOptional = bookAPIHandler.fetchSingleBook(bookNumber);
        return bookOptional.map(Book::getName);
    }

    public void enrichCharacterWithBookTitles(StoryCharacter character) {
        List<String> bookTitles = character.getBooks().stream()
                .map(url -> {
                    String bookIdStr = url.substring(url.lastIndexOf('/') + 1);
                    int bookId = Integer.parseInt(bookIdStr);
                    return getBookTitleByNumber(bookId).orElse("Unknown Title");
                })
                .collect(Collectors.toList());
        character.setBooks(bookTitles);
    }
}
