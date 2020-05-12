package com.andrewd.libs.wishlist.domain;

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
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    private long userId;

    private long bookId;

    private String imageUrl;
    private String ISBN;
    private String ISBN13;
    private String authors;

    @Lob
    @Column(nullable = false, length=8192)
    private String description;
}
