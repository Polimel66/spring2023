package org.myApplication.app.repositories;

import org.myApplication.app.Book;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("from Book")
    List<Book> findAllBooksByPageRequest(PageRequest pageRequest);
}
