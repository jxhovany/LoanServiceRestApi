package com.jehovanyr.loanservicerestapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private Long loanId;
    private double loanAmount;
    private double interestRate;
    private int numberOfYears;
    private double totalPayment;
    private double monthlyPayment;

    public void setMonthlyPayment(){
        double monthlyInterestRate = interestRate / 1200;
        this.monthlyPayment = loanAmount * monthlyInterestRate / (1 - (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
    }
    public void setTotalPayment (){
        this.totalPayment = getMonthlyPayment() * numberOfYears * 12;
    }
    public double getMonthlyPayment(){
        return this.monthlyPayment;
    }
    public double getTotalPayment(){
        return this.totalPayment;
    }


}
