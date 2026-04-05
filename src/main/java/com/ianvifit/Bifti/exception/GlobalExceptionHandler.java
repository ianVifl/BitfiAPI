package com.ianvifit.Bifti.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Esta anotación le dice a Spring: "Si alguien lanza este error en específico, ¡tráelo aquí!"
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> manejarArgumentosInvalidos(IllegalArgumentException ex) {

        Map<String , String > respuestaJSON = new HashMap<>();
        respuestaJSON.put("mensaje", ex.getMessage());
        return ResponseEntity.badRequest().body(respuestaJSON);
        //
    }


}
