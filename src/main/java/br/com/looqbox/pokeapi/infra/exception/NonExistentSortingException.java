package br.com.looqbox.pokeapi.infra.exception;

public class NonExistentSortingException extends RuntimeException {
    public NonExistentSortingException(String mensagem){
        super(mensagem);
    }
}
