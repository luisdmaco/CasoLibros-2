package com.edu.upc.CasoLibros2.repository;

import com.edu.upc.CasoLibros2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitleAndEditorial(String title, String editorial);
    List<Book> findByEditorial(String editorial);
}
