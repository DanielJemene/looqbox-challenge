package br.com.looqbox.pokeapi.controllers;

import br.com.looqbox.pokeapi.model.PokemonDTO;
import br.com.looqbox.pokeapi.model.PokemonDTOHighlight;
import br.com.looqbox.pokeapi.services.PokeApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private final PokeApiService pokeApiService;

    public PokemonController(PokeApiService pokeApiService) {
        this.pokeApiService = pokeApiService;

    }

    @GetMapping
    public ResponseEntity<PokemonDTO> getPokemons(@RequestParam(required = false) String query, @RequestParam(required = false, defaultValue = "ALPHABETICAL") String sort) {
        return ResponseEntity.ok().body(pokeApiService.getPokemon(query, sort));
    }

    @GetMapping("/highlight")
    public ResponseEntity<PokemonDTOHighlight>  getPokemonsHighlight(@RequestParam(required = false) String query, @RequestParam(required = false, defaultValue = "ALPHABETICAL") String sort) {
        PokemonDTO pokemonDTOSorted = pokeApiService.getPokemon(query, sort);
        return ResponseEntity.ok().body(pokeApiService.getPokemonHighlight(pokemonDTOSorted, query));
    }
}
