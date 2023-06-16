package com.edu.upc.CasoLibros2.service;

import com.edu.upc.CasoLibros2.dto.BookDto;
import com.edu.upc.CasoLibros2.entity.Book;

import java.util.List;

public interface BookService {
    Book createBook(Book book);
    List<BookDto> getAllBooks();

}
