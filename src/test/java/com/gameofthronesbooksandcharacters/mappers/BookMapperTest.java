package com.gameofthronesbooksandcharacters.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gameofthronesbooksandcharacters.connectivity.BookAPIHandler;
import org.junit.jupiter.api.Test;

class BookMapperTest {

    @Test
    void mapSingleBook() throws JsonProcessingException {

        String testJson = """
                {
                    "url": "https://anapioficeandfire.com/api/books/1",
                    "name": "A Game of Thrones",
                    "isbn": "978-0553103540",
                    "authors": [
                        "George R. R. Martin"
                    ],
                    "numberOfPages": 694,
                    "publisher": "Bantam Books",
                    "country": "United States",
                    "mediaType": "Hardcover",
                    "released": "1996-08-01T00:00:00",
                    "characters": [
                        "https://anapioficeandfire.com/api/characters/2"
                    ],
                    "povCharacters": [
                        "https://anapioficeandfire.com/api/characters/148"
                    ]
                }
                """;

        BookAPIHandler bookAPIHandler = new BookAPIHandler();
        System.out.println(bookAPIHandler.getSingleBook(1));

        System.out.println("---".repeat(30));


        System.out.println(bookAPIHandler.getAllBook());

    }

    @Test
    void mapAllBook() {
    }
}