package br.com.looqbox.pokeapi.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NonExistentPokemonException.class)
    public ResponseEntity nonExistentPokemonException(NonExistentPokemonException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NonExistentSortingException.class)
    public ResponseEntity nonExistentPokemonException(NonExistentSortingException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
