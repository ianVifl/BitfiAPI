package com.ianvifit.Bifti.repository;

import com.ianvifit.Bifti.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
