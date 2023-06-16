package com.edu.upc.CasoLibros2.repository;

import com.edu.upc.CasoLibros2.entity.Book;
import com.edu.upc.CasoLibros2.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//prueba
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    boolean existsByCodeStudentAndBookAndBookLoan(String codeStudent, Book book, boolean bookLoan);
    boolean existsByCodeStudent(String codeStudent);
    List<Loan> findByCodeStudent(String codeStudent);
}
