package com.ianvifit.Bifti.repository;

import com.ianvifit.Bifti.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
