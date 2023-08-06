package br.com.looqbox.pokeapi.services;

import br.com.looqbox.pokeapi.infra.exception.NonExistentSortingException;
import br.com.looqbox.pokeapi.infra.exception.NonExistentPokemonException;
import br.com.looqbox.pokeapi.model.PokemonDTO;
import br.com.looqbox.pokeapi.model.PokemonDTOHighlight;
import br.com.looqbox.pokeapi.model.PokemonNameHighlight;
import br.com.looqbox.pokeapi.ordenacao.SortEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokeApiService {

    public static final String POKEMON_API_BASE_URL ="https://pokeapi.co/api/v2/pokemon?limit=" + Integer.MAX_VALUE;

    private RestTemplate restTemplate;

    public PokeApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PokemonDTO getPokemon(String query,String sort) {
        PokemonDTO listaPokemonDTO = restTemplate.getForObject(POKEMON_API_BASE_URL, PokemonDTO.class);
        if(query == null){
            return listaPokemonDTO;
        }
        PokemonDTO PokemonDTOFilter = new PokemonDTO();
        PokemonDTOFilter.setResults(
                listaPokemonDTO.getResults().stream()
                        .filter(pokemonName -> pokemonName.getName().contains(query.toLowerCase()))
                        .collect(Collectors.toList())
        );
        if (PokemonDTOFilter.getResults().isEmpty()){
            throw new NonExistentPokemonException("There is no pokemon with this query");
        }

        return SortEnum.getEnum(sort)
                .map(sortEnum -> sortEnum.sortPokemon(PokemonDTOFilter))
                .orElseThrow(()-> new NonExistentSortingException("Nonexistent sorting"));
    }

    public PokemonDTOHighlight getPokemonHighlight(PokemonDTO pokemonDTO,String query){
        List<PokemonNameHighlight> pokemonNameHighlightList = pokemonDTO.getResults()
                .stream()
                .map(pokemonName -> {
                    String name = pokemonName.getName();
                    String queryLowerCase = query.toLowerCase();
                    String highlight = name.toLowerCase().replace(queryLowerCase, "<pre>" + queryLowerCase + "</pre>");
                    return new PokemonNameHighlight(name, highlight);
                })
                .collect(Collectors.toList());
        return new PokemonDTOHighlight(pokemonNameHighlightList);
    }
}
