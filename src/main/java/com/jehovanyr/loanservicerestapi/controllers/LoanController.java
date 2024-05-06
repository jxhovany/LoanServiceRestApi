package com.jehovanyr.loanservicerestapi.controllers;

import com.jehovanyr.loanservicerestapi.models.Loan;
import com.jehovanyr.loanservicerestapi.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LoanController {
    //Auto create the methods for repository
    @Autowired
    private LoanRepository loanRepository;

    //Handler methods:
    @GetMapping("/loan")
    public List<Loan> getAllLoans(){
        return (List<Loan>) loanRepository.findAll();
    }

    @GetMapping("/loan/{loanId}")
    public Loan getLoanByLoanId(@PathVariable Long loanId){
        return loanRepository.findByLoanId(loanId);
    }

    @PostMapping("/addLoan")
    public long saveLoan(@RequestBody Loan loan){
        if (loan.getLoanAmount() >= 500 &&
            loan.getInterestRate() > 0 && loan.getInterestRate() < 100 &&
            loan.getNumberOfYears() >= 1 && loan.getNumberOfYears() < 100){
            loan.setMonthlyPayment();
            loan.setTotalPayment();
            loanRepository.save(loan);
        }
        return loan.getId();
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteByLoadId(@PathVariable("id") long id){
        loanRepository.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllLoans(){
        loanRepository.deleteAll();
    }

    @PutMapping("/updateLoanById/{id}")
    public void updateLoan(@PathVariable("id") long loanId, @RequestBody Loan loanObj){
        Optional<Loan> loanData = loanRepository.findById(loanId);
        Loan l = loanData.get();
        l.setLoanAmount(loanObj.getLoanAmount());
        l.setLoanId(loanObj.getLoanId());
        l.setInterestRate(loanObj.getInterestRate());
        l.setNumberOfYears(loanObj.getNumberOfYears());
        loanRepository.save(l);
    }



}
