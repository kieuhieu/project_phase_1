package com.klb.book.controller;

import com.klb.book.entity.Book;
import com.klb.book.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping
    public List<Book> findAllBooks(){
        return bookService.findAllBooks();
    }
}
