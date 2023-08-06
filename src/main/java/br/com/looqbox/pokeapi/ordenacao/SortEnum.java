package br.com.looqbox.pokeapi.ordenacao;

import br.com.looqbox.pokeapi.model.PokemonDTO;
import br.com.looqbox.pokeapi.model.PokemonName;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public enum SortEnum {
    ALPHABETICAL(SortEnum::quickSortAlphabetical),
    LENGTH(SortEnum::quickSortLength);

    private Function<PokemonDTO, PokemonDTO> sortFunction;

    SortEnum(Function<PokemonDTO,PokemonDTO> sortFunction) {
        this.sortFunction = sortFunction;
    }

    public static Optional<SortEnum> getEnum(String sort){
        return Stream.of(SortEnum.values())
                .filter(value->value.name().equalsIgnoreCase(sort))
                .findFirst();
    }

    public PokemonDTO sortPokemon(PokemonDTO pokemonDTO){
        return sortFunction.apply(pokemonDTO);
    }


    //O algoritmo implementado foi o Quick Sort, que consiste em escolher um pivo e dividir a lista em sublistas a esquerda
    //e a direita do pivo, dessa forma, não sera preciso percorrer o array inteiro em todos os casos, fazendo desse algortimo
    //um dos mais efecientes nessa condição de ordenação. A complexidade média dele é de O(n log n).
    public static PokemonDTO quickSortAlphabetical(PokemonDTO pokemonDTO){
        List<PokemonName> names =  pokemonDTO.getResults();
        quickSortAlphabetical(names, 0, names.size() - 1);
        return new PokemonDTO(names);
    }
    public static void quickSortAlphabetical(List<PokemonName> names, int start, int end){
        PokemonName pivot = names.get(start);
        int l = start;
        int r = end;

       while (l<=r){
           while (names.get(l).getName().compareTo(pivot.getName()) < 0){
               l++;
           }
           while (names.get(r).getName().compareTo(pivot.getName()) > 0){
                r--;
           }

           if (l<=r) {
               PokemonName nomeTemp = names.get(l);
               names.set(l, names.get(r));
               names.set(r, nomeTemp);
               l++;
               r--;
           }
           if (start<r) quickSortAlphabetical(names, start, r);
           if (l<end) quickSortAlphabetical(names, l, end);
       }

    }

    public static PokemonDTO quickSortLength(PokemonDTO pokemonDTO){
        List<PokemonName> names =  pokemonDTO.getResults();
        quickSortLength(names, 0, names.size() - 1);
        return new PokemonDTO(names);
    }
    public static void quickSortLength(List<PokemonName> names, int start, int end){
        PokemonName pivot = names.get(start);
        int l = start;
        int r = end;

        while (l<=r){
            while (compararTamanhoEOrdem(names.get(l).getName(),pivot.getName()) < 0){
                l++;
            }
            while (compararTamanhoEOrdem(names.get(r).getName(),pivot.getName()) > 0){
                r--;
            }

            if (l<=r) {
                PokemonName nomeTemp = names.get(l);
                names.set(l, names.get(r));
                names.set(r, nomeTemp);
                l++;
                r--;
            }
            if (start<r) quickSortLength(names, start, r);
            if (l<end) quickSortLength(names, l, end);
        }

    }

    //Esse método é nescessário, pois o algoritmo de ordenanção de tamanho precisa de algum outro parâmetro para fazer comparação caso
    //o tamanho da String seja igual, no caso, o outro parâmetro utilizado foi a ordem alfabetica.
    public static int compararTamanhoEOrdem(String nome1, String nome2){
        if (nome1.length() > nome2.length()){
            return 1;
        } else if (nome1.compareTo(nome2) > 0 && nome1.length() == nome2.length()){
            return 1;
        } else if(nome1.equals(nome2)) return 0;
        return -1;

    }

}
