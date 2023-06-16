package com.edu.upc.CasoLibros2.service.impl;

import com.edu.upc.CasoLibros2.model.Book;
import com.edu.upc.CasoLibros2.repository.BookRepository;
import com.edu.upc.CasoLibros2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
}
