package com.ianvifit.Bifti.repository;

import com.ianvifit.Bifti.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> { //nombre de la clase y el tipo de dato del id de la clase
}
