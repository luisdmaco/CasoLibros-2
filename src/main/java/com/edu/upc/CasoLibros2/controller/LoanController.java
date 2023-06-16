package com.edu.upc.CasoLibros2.controller;

import com.edu.upc.CasoLibros2.exception.ResourceNotFoundException;
import com.edu.upc.CasoLibros2.exception.ValidationException;
import com.edu.upc.CasoLibros2.entity.Book;
import com.edu.upc.CasoLibros2.entity.Loan;
import com.edu.upc.CasoLibros2.repository.BookRepository;
import com.edu.upc.CasoLibros2.repository.LoanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/library/v1")
public class LoanController {
    private LoanRepository loanRepository;
    private BookRepository bookRepository;

    public LoanController(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    //EndPoint: http://localhost:8080/api/library/v1/books/filterByCodeStudent
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/loans/filterByCodeStudent")
    public ResponseEntity<List<Loan>> getAllLoansByCodeStudent(@RequestParam String codeStudent){
        return new ResponseEntity<List<Loan>>(loanRepository.findByCodeStudent(codeStudent), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/library/v1/books/1/loans
    //Method: POST
    @Transactional
    @RequestMapping("/books/{id}/loans")
    public ResponseEntity<Loan> createLoan(long bookId, Loan loan) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el libro con el id: " + bookId));
        existsByCodeStudentAndBookAndBookLoan(loan, book);
        validateLoan(loan);
        loan.setBookLoan(true);
        return new ResponseEntity<Loan>(loanRepository.save(loan), HttpStatus.CREATED);
    }

    private void existsByCodeStudentAndBookAndBookLoan(Loan loan, Book book) {
        if(loanRepository.existsByCodeStudentAndBookAndBookLoan(loan.getCodeStudent(), book, true)) {
            throw new ValidationException("El estudiante ya tiene prestado el libro");
        }
    }

    private void validateLoan(Loan loan) {
        if(loan.getCodeStudent() == null || loan.getCodeStudent().isEmpty()) {
            throw new ValidationException("El código de estudiante es obligatorio");
        }
        if(loan.getCodeStudent().length() > 10) {
            throw new ValidationException("El código de estudiante no puede tener 10 caracteres");
        }
    }
}
