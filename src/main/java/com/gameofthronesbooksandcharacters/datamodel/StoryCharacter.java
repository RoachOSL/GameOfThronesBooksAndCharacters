package com.gameofthronesbooksandcharacters.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
public class StoryCharacter {
    private String name;
    private String gender;
    private String culture;
    private String born;
    private String died;
    private List<String> titles;
    private List<String> aliases;
    private String father;
    private String mother;
    private String spouse;
    private List<String> allegiances;
    private List<String> books;
    private List<String> tvSeries;
    private List<String> playedBy;

    public StoryCharacter(@JsonProperty("name") String name,
                          @JsonProperty("gender") String gender,
                          @JsonProperty("culture") String culture,
                          @JsonProperty("born") String born,
                          @JsonProperty("died") String died,
                          @JsonProperty("titles") List<String> titles,
                          @JsonProperty("aliases") List<String> aliases,
                          @JsonProperty("father") String father,
                          @JsonProperty("mother") String mother,
                          @JsonProperty("spouse") String spouse,
                          @JsonProperty("allegiances") List<String> allegiances,
                          @JsonProperty("books") List<String> books,
                          @JsonProperty("tvSeries") List<String> tvSeries,
                          @JsonProperty("playedBy") List<String> playedBy) {
        this.name = name;
        this.gender = gender;
        this.culture = culture;
        this.born = born;
        this.died = died;
        this.titles = titles;
        this.aliases = aliases;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
        this.allegiances = allegiances;
        this.books = books;
        this.tvSeries = tvSeries;
        this.playedBy = playedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoryCharacter storyCharacter = (StoryCharacter) o;
        return Objects.equals(name, storyCharacter.name) && Objects.equals(gender, storyCharacter.gender)
                && Objects.equals(culture, storyCharacter.culture) && Objects.equals(born, storyCharacter.born)
                && Objects.equals(died, storyCharacter.died) && Objects.equals(titles, storyCharacter.titles)
                && Objects.equals(aliases, storyCharacter.aliases) && Objects.equals(father, storyCharacter.father)
                && Objects.equals(mother, storyCharacter.mother) && Objects.equals(spouse, storyCharacter.spouse)
                && Objects.equals(allegiances, storyCharacter.allegiances) && Objects.equals(books,
                storyCharacter.books) && Objects.equals(tvSeries, storyCharacter.tvSeries) && Objects.equals(playedBy,
                storyCharacter.playedBy);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, gender, culture, born, died, titles, aliases, father, mother, spouse,
                allegiances, books, tvSeries, playedBy);
        result = 37 * result;
        return result;
    }
}
