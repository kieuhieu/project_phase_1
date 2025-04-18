package com.klb.book.service;

import com.klb.book.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();
}
