package br.com.looqbox.pokeapi.infra.exception;

public class NonExistentPokemonException extends RuntimeException {
    public NonExistentPokemonException(String mensagem) {
        super(mensagem);
    }
}
