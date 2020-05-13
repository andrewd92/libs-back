package com.andrewd.libs.reading.api.controller;

import javax.validation.Valid;
import java.util.List;

import com.andrewd.libs.reading.api.request.AddReadingBookRequest;
import com.andrewd.libs.reading.domain.ReadingBook;
import com.andrewd.libs.reading.service.ReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reading/v1")
@CrossOrigin()
@RequiredArgsConstructor
public class ReadingController {

    private final ReadingService service;

    @GetMapping("/{userId}")
    public List<ReadingBook> get(@PathVariable long userId) {
        return service.get(userId);
    }

    @PostMapping("/")
    public ReadingBook add(@Valid @RequestBody AddReadingBookRequest request) {
        return service.add(request.getUserId(), request.getBookId());
    }
}
