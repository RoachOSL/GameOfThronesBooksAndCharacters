package com.gameofthronesbooksandcharacters.controllers;

import com.gameofthronesbooksandcharacters.connectivity.BookAPIHandler;
import com.gameofthronesbooksandcharacters.datamodel.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    private final BookAPIHandler apiHandler = new BookAPIHandler();

    @RequestMapping("/books")
    public String getBookList(Model model) {
        List<Book> booksList = apiHandler.fetchAllBook();
        if (booksList == null) {
            booksList = new ArrayList<>();
        }
        model.addAttribute("booksList", booksList);
        return "books";
    }

    @RequestMapping("/book")
    public String getSingleBook(Model model, @RequestParam Integer id) {
        Optional<Book> bookOpt = apiHandler.fetchSingleBook(id);
        if (bookOpt.isPresent()) {
            model.addAttribute("book", bookOpt.get());
            return "book";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }
}
