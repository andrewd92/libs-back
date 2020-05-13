package com.andrewd.libs.reading.domain;

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
@Table(name = "reading_book", indexes = {
        @Index(name = "reading_book_unique_idx", columnList = "userId,bookId", unique = true)
})
public class ReadingBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    private long userId;

    private long bookId;

    private String imageUrl;
    private String authors;

    @Lob
    @Column(nullable = false, length=8192)
    private String description;
}
