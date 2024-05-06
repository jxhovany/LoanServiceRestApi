package com.jehovanyr.loanservicerestapi.repositories;

import com.jehovanyr.loanservicerestapi.models.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    Loan findByLoanId(Long loanId);
}
