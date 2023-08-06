package br.com.looqbox.pokeapi.model;

import java.util.Objects;

public class PokemonNameHighlight {
    private String name;
    private String highlight;

    public PokemonNameHighlight(String name, String highlight) {
        this.name = name;
        this.highlight = highlight;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        PokemonNameHighlight that = (PokemonNameHighlight) o;
        return Objects.equals(this.getName(), that.getName())
                && Objects.equals(this.getHighlight(), that.getHighlight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHighlight());
    }
}
