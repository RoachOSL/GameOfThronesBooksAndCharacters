package com.gameofthronesbooksandcharacters.controllers;

import com.gameofthronesbooksandcharacters.connectivity.BookAPIHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    private final BookAPIHandler apiHandler = new BookAPIHandler();

    @RequestMapping("/books")
    public String getBookList(Model model) {
        model.addAttribute("booksList", apiHandler.fetchAllBook());
        return "books";
    }

    @RequestMapping("/book")
    public String getSingleBook(Model model, @RequestParam Integer id) {
        model.addAttribute("book", apiHandler.fetchSingleBook(id));
        return "book";
    }
}
