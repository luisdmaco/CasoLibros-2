package com.edu.upc.CasoLibros2.repository;

import com.edu.upc.CasoLibros2.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanInterface extends JpaRepository<Loan, Long> {
    boolean existsByCodeStudent(String codeStudent);
    List<Loan> findByCodeStudent(String codeStudent);
}
