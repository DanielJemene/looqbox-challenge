package br.com.looqbox.pokeapi.model;

import java.util.List;

public class PokemonDTO {

    private List<PokemonName> results;

    public PokemonDTO(){}

    public PokemonDTO(List<PokemonName> results){
        this.results = results;
    }


    public List<PokemonName> getResults() {
        return results;
    }

    public void setResults(List<PokemonName> results) {
        this.results = results;
    }

}
