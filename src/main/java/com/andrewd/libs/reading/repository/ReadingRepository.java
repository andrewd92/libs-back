package com.andrewd.libs.reading.repository;

import java.util.List;
import java.util.Optional;

import com.andrewd.libs.reading.domain.ReadingBook;
import org.springframework.data.repository.CrudRepository;

public interface ReadingRepository extends CrudRepository<ReadingBook, Long> {
    List<ReadingBook> findAllByUserId(long userId);

     Optional<ReadingBook> findByUserIdAndBookId(long userId, long bookId);
}
