package com.andrewd.libs.reading.repository;

import java.util.List;

import com.andrewd.libs.reading.domain.ReadingBook;
import org.springframework.data.repository.CrudRepository;

public interface ReadingRepository extends CrudRepository<ReadingBook, Long> {
    List<ReadingBook> findAllByUserId(long userId);
}
