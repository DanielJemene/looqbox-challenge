package br.com.looqbox.pokeapi.service;

import br.com.looqbox.pokeapi.infra.exception.NonExistentPokemonException;
import br.com.looqbox.pokeapi.infra.exception.NonExistentSortingException;
import br.com.looqbox.pokeapi.model.PokemonDTO;
import br.com.looqbox.pokeapi.model.PokemonDTOHighlight;
import br.com.looqbox.pokeapi.model.PokemonName;
import br.com.looqbox.pokeapi.model.PokemonNameHighlight;
import br.com.looqbox.pokeapi.services.PokeApiService;


import static br.com.looqbox.pokeapi.services.PokeApiService.POKEMON_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

class PokemonApiServiceTest {

    @InjectMocks
    private PokeApiService service;
    private PokemonDTOHighlight pokemonDTOHighlightExpected;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        Mockito.when(restTemplate.getForObject(POKEMON_API_BASE_URL, PokemonDTO.class)).thenReturn(generatePokemonDTO());
    }

    @Test
    void getPokemonsSortAlphabeticalTest() {
        String query = "zi";
        String sort = "Alphabetical";
        this.pokemonDTOHighlightExpected = generatePokemonDTOHighlightAlphabetical();
        PokemonDTO pokemonDTO = service.getPokemon(query, sort);
        PokemonDTOHighlight pokemonDTOHighlight = service.getPokemonHighlight(pokemonDTO, query);

        assertEquals(pokemonDTOHighlight.getResults(), pokemonDTOHighlightExpected.getResults());
    }

    @Test
    void getPokemonsSortLengthTest() {
        String query = "zi";
        String sort = "Length";
        this.pokemonDTOHighlightExpected = generatePokemonDTOHighlightLength();
        PokemonDTO pokemonDTO = service.getPokemon(query, sort);
        PokemonDTOHighlight pokemonDTOHighlight = service.getPokemonHighlight(pokemonDTO, query);

        assertEquals(pokemonDTOHighlight.getResults(), pokemonDTOHighlightExpected.getResults());
    }

    @Test
    void nonExistentPokemon(){
        String query = "zii";
        String sort = "Length";
        this.pokemonDTOHighlightExpected = generatePokemonDTOHighlightLength();
        Assertions.assertThrows(NonExistentPokemonException.class, ()-> service.getPokemon(query, sort));
    }

    @Test
    void NonExistentSorting(){
        String query = "zi";
        String sort = "Lengthh";
        this.pokemonDTOHighlightExpected = generatePokemonDTOHighlightLength();
        Assertions.assertThrows(NonExistentSortingException.class, ()-> service.getPokemon(query, sort));
    }

    private PokemonDTOHighlight generatePokemonDTOHighlightAlphabetical() {
        List<PokemonNameHighlight> pokemonNameList = new ArrayList<>();

        pokemonNameList.add(new PokemonNameHighlight("blaziken","bla<pre>zi</pre>ken"));
        pokemonNameList.add(new PokemonNameHighlight("blaziken-mega","bla<pre>zi</pre>ken-mega"));
        pokemonNameList.add(new PokemonNameHighlight("drizzile","driz<pre>zi</pre>le"));
        pokemonNameList.add(new PokemonNameHighlight("virizion","viri<pre>zi</pre>on"));
        pokemonNameList.add(new PokemonNameHighlight("weezing","wee<pre>zi</pre>ng"));
        pokemonNameList.add(new PokemonNameHighlight("weezing-galar","wee<pre>zi</pre>ng-galar"));
        pokemonNameList.add(new PokemonNameHighlight("zigzagoon","<pre>zi</pre>gzagoon"));
        pokemonNameList.add(new PokemonNameHighlight("zigzagoon-galar","<pre>zi</pre>gzagoon-galar"));

        return new PokemonDTOHighlight(pokemonNameList);
    }

    private PokemonDTOHighlight generatePokemonDTOHighlightLength() {
        List<PokemonNameHighlight> pokemonNameList = new ArrayList<>();

        pokemonNameList.add(new PokemonNameHighlight("weezing","wee<pre>zi</pre>ng"));
        pokemonNameList.add(new PokemonNameHighlight("blaziken","bla<pre>zi</pre>ken"));
        pokemonNameList.add(new PokemonNameHighlight("drizzile","driz<pre>zi</pre>le"));
        pokemonNameList.add(new PokemonNameHighlight("virizion","viri<pre>zi</pre>on"));
        pokemonNameList.add(new PokemonNameHighlight("zigzagoon","<pre>zi</pre>gzagoon"));
        pokemonNameList.add(new PokemonNameHighlight("blaziken-mega","bla<pre>zi</pre>ken-mega"));
        pokemonNameList.add(new PokemonNameHighlight("weezing-galar","wee<pre>zi</pre>ng-galar"));
        pokemonNameList.add(new PokemonNameHighlight("zigzagoon-galar","<pre>zi</pre>gzagoon-galar"));

        return new PokemonDTOHighlight(pokemonNameList);
    }

    private PokemonDTO generatePokemonDTO() {
        List<PokemonName> pokemonNameList = new ArrayList<>();

        pokemonNameList.add(new PokemonName("weezing"));
        pokemonNameList.add(new PokemonName("blaziken"));
        pokemonNameList.add(new PokemonName("caterpie"));
        pokemonNameList.add(new PokemonName("zigzagoon"));
        pokemonNameList.add(new PokemonName("virizion"));
        pokemonNameList.add(new PokemonName("pikachu"));
        pokemonNameList.add(new PokemonName("drizzile"));
        pokemonNameList.add(new PokemonName("blaziken-mega"));
        pokemonNameList.add(new PokemonName("weezing-galar"));
        pokemonNameList.add(new PokemonName("zigzagoon-galar"));
        pokemonNameList.add(new PokemonName("cramorant-gulping"));

        return new PokemonDTO(pokemonNameList);
    }

}
