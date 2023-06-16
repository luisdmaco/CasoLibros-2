package com.edu.upc.CasoLibros2.service.impl;

import com.edu.upc.CasoLibros2.dto.BookDto;
import com.edu.upc.CasoLibros2.entity.Book;
import com.edu.upc.CasoLibros2.repository.BookRepository;
import com.edu.upc.CasoLibros2.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<BookDto> getAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    public BookDto createBook(BookDto bookDto){
        Book book = DtoToEntity(bookDto);
        return EntityToDto(bookRepository.save(book));
    }

    // Sale de nuestro programa a la DB
    private BookDto EntityToDto(Book book){
        return modelMapper.map(book, BookDto.class);
    }
    // Sale de la DB a nuestro programa
    private Book DtoToEntity(BookDto bookDto){
        return modelMapper.map(bookDto, Book.class);
    }
}
