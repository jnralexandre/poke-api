package br.org.fundatec.pokeapi.repository;

import br.org.fundatec.pokeapi.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    public Pokemon findByNome(String nome);

    List<Pokemon> findByTipo(String tipo);
}
