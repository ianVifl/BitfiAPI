package com.ianvifit.Bifti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
// habilita el soporte para auditoria en JPA, lo que permite registrar automaticamente la fecha de creacion y actualizacion de las entidades
public class BiftiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiftiApplication.class, args);
	}

}
