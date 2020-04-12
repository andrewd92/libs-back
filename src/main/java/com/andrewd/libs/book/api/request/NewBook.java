package com.andrewd.libs.book.api.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBook {

    @JsonProperty("title")
    @NotNull
    private String title;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("ISBN")
    @NotNull
    private String ISBN;

    @JsonProperty("ISBN13")
    private String ISBN13;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("authors")
    @NotNull
    private String authors;

    @JsonProperty("edition")
    private int edition;

    @JsonProperty("publishDate")
    private String publishDate;

    @JsonProperty("pages")
    private int pages;

    @JsonProperty("description")
    private String description;
}
