package br.com.looqbox.pokeapi.model;

public class PokemonName {
    private String name;

    public PokemonName(){}

    public PokemonName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}