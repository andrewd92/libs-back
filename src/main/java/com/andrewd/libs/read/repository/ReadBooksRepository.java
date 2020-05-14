package com.andrewd.libs.read.repository;

import java.util.List;

import com.andrewd.libs.read.domain.ReadBook;
import org.springframework.data.repository.CrudRepository;

public interface ReadBooksRepository extends CrudRepository<ReadBook, Long> {
    List<ReadBook> findAllByUserId(long userId);
}
