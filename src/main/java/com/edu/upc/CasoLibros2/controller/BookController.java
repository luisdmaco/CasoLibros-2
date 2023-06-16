package com.edu.upc.CasoLibros2.controller;

import com.edu.upc.CasoLibros2.dto.BookDto;
import com.edu.upc.CasoLibros2.exception.ValidationException;
import com.edu.upc.CasoLibros2.entity.Book;
import com.edu.upc.CasoLibros2.repository.BookRepository;
import com.edu.upc.CasoLibros2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library/v1")
public class BookController {
    @Autowired
    private BookService bookService;
    private final BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

/*    //EndPoint: http://localhost:8080/api/library/v1/books
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<List<Book>>(bookRepository.findAll(), HttpStatus.OK);
    }*/

    @GetMapping("/books")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

/*    //EndPoint: http://localhost:8080/api/library/v1/books
    //Method: POST
    @Transactional
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        existsByTitleAndEditorial(book);
        validateBook(book);
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    } */

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        existsByTitleAndEditorial(book);
        validateBook(book);
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    public void validateBook(Book book) {
        if(book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new ValidationException("El titulo del libro debe ser obligatorio.");
        }
        if(book.getTitle().length() > 22) {
            throw new ValidationException("El titulo del libro no debe exceder los 22 caracteres.");
        }
        if(book.getEditorial() == null || book.getEditorial().trim().isEmpty()) {
            throw new ValidationException("La editorial del libro debe ser obligatorio.");
        }
        if(book.getEditorial().length() > 14) {
            throw new ValidationException("La editorial del libro no debe exceder los 14 caracteres.");
        }
    }

    private void existsByTitleAndEditorial(Book book) {
        if(bookRepository.existsByTitleAndEditorial(book.getTitle(), book.getEditorial())) {
            throw new ValidationException("El libro ya existe.");
        }
    }
}
