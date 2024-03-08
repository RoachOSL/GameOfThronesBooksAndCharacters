package com.gameofthronesbooksandcharacters.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Book {
    private String name;
    private String isbn;
    private List<String> authors;
    private Integer numberOfPages;
    private String publisher;
    private String countryOfOrigin;
    private String mediaType;
    private LocalDate date;
    private List<String> characters;

    public Book(@JsonProperty("name") String name,
                @JsonProperty("isbn") String isbn,
                @JsonProperty("authors") List<String> authors,
                @JsonProperty("numberOfPages") Integer numberOfPages,
                @JsonProperty("publisher") String publisher,
                @JsonProperty("country") String countryOfOrigin,
                @JsonProperty("mediaType") String mediaType,
                @JsonProperty("released") LocalDate date,
                @JsonProperty("characters") List<String> characters) {
        this.name = name;
        this.isbn = isbn;
        this.authors = authors;
        this.numberOfPages = numberOfPages;
        this.publisher = publisher;
        this.countryOfOrigin = countryOfOrigin;
        this.mediaType = mediaType;
        this.date = date;
        this.characters = characters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) && Objects.equals(isbn, book.isbn) && Objects.equals(authors,
                book.authors) && Objects.equals(numberOfPages, book.numberOfPages) && Objects.equals(publisher,
                book.publisher) && Objects.equals(countryOfOrigin, book.countryOfOrigin) && Objects.equals(mediaType,
                book.mediaType) && Objects.equals(date, book.date) && Objects.equals(characters, book.characters);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, isbn, authors, numberOfPages, publisher, countryOfOrigin, mediaType,
                date, characters);
        result = 37 * result;
        return result;
    }
}
