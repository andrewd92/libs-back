package com.andrewd.libs.wishlist.repository;

import com.andrewd.libs.wishlist.domain.WishList;
import org.springframework.data.repository.CrudRepository;

public interface WishListRepository extends CrudRepository<WishList, Long> {
}
