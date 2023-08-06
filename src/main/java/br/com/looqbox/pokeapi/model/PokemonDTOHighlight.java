package br.com.looqbox.pokeapi.model;

import java.util.List;
import java.util.Objects;

public class PokemonDTOHighlight {

    private List<PokemonNameHighlight> results;

    public PokemonDTOHighlight(List<PokemonNameHighlight> pokemonNameList) {
        this.results = pokemonNameList;
    }

    public List<PokemonNameHighlight> getResults() {
        return results;
    }

    public void setResults(List<PokemonNameHighlight> results) {
        this.results = results;
    }


}
