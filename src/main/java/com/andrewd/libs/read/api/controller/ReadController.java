package com.andrewd.libs.read.api.controller;

import javax.validation.Valid;

import java.util.List;

import com.andrewd.libs.read.api.request.BookReadRequest;
import com.andrewd.libs.read.domain.ReadBook;
import com.andrewd.libs.read.service.ReadBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/read/v1/")
public class ReadController {

    private final ReadBookService service;

    @PostMapping("/")
    public ReadBook read(@Valid @RequestBody BookReadRequest request) {
        return service.read(request.getUserId(), request.getBookId());
    }

    @GetMapping("/{userId}")
    public List<ReadBook> get(@PathVariable long userId) {
        return service.getForUser(userId);
    }
}
