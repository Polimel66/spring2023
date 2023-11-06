package org.myApplication.extern.controllers;

import lombok.AllArgsConstructor;
import org.myApplication.app.Book;
import org.myApplication.app.User;
import org.myApplication.app.interfaces.BookService;
import org.myApplication.app.interfaces.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    private UserService userService;

    @PostMapping("/saveBook/{userId}")
    public Book saveBook(@RequestBody Book book, @PathVariable Long userId) {
        Optional<User> user = userService.findUserById(userId);
        book.setBookOwner(user.get());
        return bookService.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
    }

    @GetMapping("/{id}")
    public Optional<Book> getBook(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/findAll")
    public List<Book> getAllBooks(@RequestParam(required = false, defaultValue = "0") int page,
                                  @RequestParam(required = false, defaultValue = "10") int numberEntries) {
        return bookService.findAllBooks(PageRequest.of(page, numberEntries));
    }

    @PutMapping
    public Book changeBook(Book changedBook) {
        return bookService.changeBook(changedBook);
    }
}
