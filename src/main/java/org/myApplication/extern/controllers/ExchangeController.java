package org.myApplication.extern.controllers;

import org.myApplication.app.services.ExchangeServiceImpl;
import org.myApplication.extern.converters.BookConverter;
import org.myApplication.extern.models.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    @Autowired
    private ExchangeServiceImpl exchangeService;
    @Autowired
    private BookConverter bookConverter;

    @PostMapping("/add")
    public void addExchange(@RequestParam(required = false, defaultValue = "0") Long requestingUserId,
                            @RequestParam(required = false, defaultValue = "0") Long respondingUserId,
                            @RequestParam(required = false, defaultValue = "0") Long requestedBookId) {
        exchangeService.addExchange(requestingUserId, respondingUserId, requestedBookId);
    }

    @DeleteMapping("/{id}")
    public void deleteExchange(@PathVariable("id") Long id) {
        exchangeService.deleteExchange(id);
    }

    @GetMapping("/suggestedBooks/{id}")
    public List<BookModel> getSuggestedBooks(@PathVariable("id") Long id) {
        return exchangeService
                .getSuggestedBooks(id)
                .stream()
                .map(bookEntity -> bookConverter.toModel(bookEntity))
                .collect(Collectors.toList());
    }

    @GetMapping("/accept/{id}")
    public List<String> acceptExchange(@PathVariable("id") Long id) {
        return exchangeService.acceptExchange(id);
    }

    @GetMapping("/reject/{id}")
    public void rejectExchange(@PathVariable("id") Long id) {
        exchangeService.rejectExchange(id);
    }

    @GetMapping("/perform/{exchangeId}")
    public void performExchange(@PathVariable("exchangeId") Long exchangeId,
                                @RequestParam Long libraryBookId) {
        exchangeService.performExchange(exchangeId, libraryBookId);
    }
}
