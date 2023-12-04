package org.myApplication.extern.controllers;

import org.myApplication.app.filter.CriteriaModel;
import org.myApplication.app.interfaces.BookService;
import org.myApplication.extern.converters.BookConverter;
import org.myApplication.extern.models.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookConverter bookConverter;

    @PostMapping("/save/{userId}")
    public BookModel saveBook(@RequestBody BookModel book, @PathVariable Long userId) {
        return bookConverter.toModel(bookService.saveBook(bookConverter.toEntity(book), userId));
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
    }

    @GetMapping("/{id}")
    public BookModel getBook(@PathVariable("id") Long id) {
        return bookConverter.toModel(bookService.findBookById(id).get());
    }

    @GetMapping("/findAll")
    public List<BookModel> getAllBooks(Pageable newPageable) {
        return bookService.findAllBooks(newPageable).stream()
                .map(bookEntity -> bookConverter.toModel(bookEntity)).collect(Collectors.toList());
    }

    @GetMapping("/searchBook")
    public List<BookModel> searchBooks(Pageable newPageable,
                                       @RequestBody List<CriteriaModel> criteriaModels) {
        return bookService.searchBooks(newPageable, criteriaModels).stream()
                .map(bookEntity -> bookConverter.toModel(bookEntity))
                .collect(Collectors.toList());
    }

    @PutMapping("/{bookId}")
    public BookModel changeBook(@RequestBody BookModel changedBook, @PathVariable("bookId") Long id) {
        return bookConverter.toModel(bookService.changeBook(bookConverter.toEntity(changedBook), id));
    }

    @GetMapping("/filter")
    public List<BookModel> filterBooks(@RequestBody List<CriteriaModel> criteriaModels) {
        return bookService.filterBooks(criteriaModels).stream()
                .map(bookEntity -> bookConverter.toModel(bookEntity)).collect(Collectors.toList());
    }
}
