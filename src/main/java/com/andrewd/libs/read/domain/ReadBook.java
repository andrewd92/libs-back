package com.andrewd.libs.read.domain;

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
@Table(name = "read_book", indexes = {
        @Index(name = "read_book_unique_idx", columnList = "userId,bookId", unique = true)
})
public class ReadBook {
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
