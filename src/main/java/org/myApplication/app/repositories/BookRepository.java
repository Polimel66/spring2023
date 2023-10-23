package org.myApplication.app.repositories;

import org.myApplication.app.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
