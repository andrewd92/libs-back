package com.andrewd.libs.book.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    private String imageUrl;
    private String ISBN;
    private String ISBN13;
    private String publisher;
    private String authors;
    private int edition;
    private String publishDate;
    private int pages;

    @Lob
    @Column(nullable = false, length=8192)
    private String description;
}
