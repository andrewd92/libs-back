package com.andrewd.libs.wishlist.api.controller;

import javax.validation.Valid;

import java.util.List;

import com.andrewd.libs.wishlist.api.request.AddWishRequest;
import com.andrewd.libs.wishlist.domain.WishList;
import com.andrewd.libs.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist/v1")
@CrossOrigin()
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @PostMapping("/")
    public WishList add(@Valid @RequestBody AddWishRequest request) {
        return wishListService.addToWishList(request.getUserId(), request.getBookId());
    }

    @GetMapping("/{userId}")
    public List<WishList> get(@PathVariable long userId) {
        return wishListService.getFor(userId);
    }
}
