package org.myApplication.extern.controllers;

import lombok.var;
import org.myApplication.app.Entities.BookEntity;
import org.myApplication.app.Entities.UserEntity;
import org.myApplication.app.repositories.BookRepository;
import org.myApplication.app.repositories.UserRepository;
import org.myApplication.extern.Converters.BookConverter;
import org.myApplication.extern.Models.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookConverter bookConverter;

    @PostMapping("/save/{userId}")
    public BookModel saveBook(@RequestBody BookModel book, @PathVariable Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        BookEntity bookEntity = bookConverter.toEntity(book);
        bookEntity.setBookOwner(user.get());
        return bookConverter.toModel(bookRepository.save(bookEntity));
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public BookModel getBook(@PathVariable("id") Long id) {
        return bookConverter.toModel(bookRepository.findById(id).get());
    }

    @GetMapping("/findAll")
    public List<BookModel> getAllBooks(@RequestParam(required = false, defaultValue = "0") int page,
                                       @RequestParam(required = false, defaultValue = "10") int numberEntries,
                                       @RequestParam(required = false, defaultValue = "title") String sortingParameter,
                                       @RequestParam(required = false, defaultValue = "ASC") String sortingDirection) {
        return bookRepository.findAllBooksByPageRequest(PageRequest.of(page, numberEntries,
                        Sort.by(Sort.Direction.valueOf(sortingDirection), sortingParameter))).stream()
                .map(bookEntity -> bookConverter.toModel(bookEntity)).collect(Collectors.toList());
    }

    @PostMapping("/change/{id}")
    public BookModel changeBook(@RequestBody BookModel changedBook, @PathVariable("id") Long id) {
        BookEntity changedBookEntity = bookConverter.toEntity(changedBook);
        changedBookEntity.setId(id);
        changedBookEntity.setBookOwner(bookRepository.findById(id).get().getBookOwner());
        return bookConverter.toModel(bookRepository.save(changedBookEntity));
    }

    @GetMapping("/searchBooks/{str}")
    public List<BookModel> searchBooks(@PathVariable("str") String str,
                                       @RequestParam(required = false, defaultValue = "0") int page,
                                       @RequestParam(required = false, defaultValue = "10") int numberEntries,
                                       @RequestParam(required = false, defaultValue = "title") String sortingParameter,
                                       @RequestParam(required = false, defaultValue = "ASC") String sortingDirection
    ) {
        var searchStr = "%" + str + "%";
        return bookRepository.findByTitleOrAuthorByPageRequest(searchStr, searchStr,
                        PageRequest.of(page, numberEntries, Sort.by(Sort.Direction.valueOf(sortingDirection),
                                sortingParameter))).stream().map(bookEntity -> bookConverter.toModel(bookEntity))
                .collect(Collectors.toList());
    }
}
