package br.org.fundatec.pokeapi.service;

import br.org.fundatec.pokeapi.model.Pokemon;
import br.org.fundatec.pokeapi.model.dto.PokemonRequestDTO;
import br.org.fundatec.pokeapi.model.dto.converter.PokemonConverter;
import br.org.fundatec.pokeapi.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PokemonService {

    private PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> buscarTodos() {
        return pokemonRepository.findAll();
    }

    public Pokemon buscarPokemonPorNome(String nome) {
        return pokemonRepository.findByNome(nome);
    }

    public void adicionar(PokemonRequestDTO pokemonRequestDTO) {
        String nomePokemonASerAdicionado = pokemonRequestDTO.getNome();
        Pokemon pokemonExistente = buscarPokemonPorNome(nomePokemonASerAdicionado);

        // Verificar se Pokémon já existe no Banco de Dados.
        if (pokemonExistente != null) {
            // Retornar HTTP Status Code: 400
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Não é possível adicionar um Pokémon repetido." + " O Pokémon " + nomePokemonASerAdicionado + " já existe no Banco de Dados."
            );
        } else {
            Pokemon pokemonEntity = PokemonConverter.converterParaEntidade(pokemonRequestDTO);
            pokemonRepository.save(pokemonEntity);
        }
    }

    public Pokemon removerPorId(Long id) {
        Pokemon pokemonParaRemover = pokemonRepository.findById(id).get();
        pokemonRepository.deleteById(id);
        return pokemonParaRemover;
    }

    public Pokemon removerPorNome(String nome) {
        Pokemon pokemonParaRemover = pokemonRepository.findByNome(nome);

        if (pokemonParaRemover == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Não é possível remover um Pokémon inexistente." + " O Pokémon " + nome + " Não existe no Banco de Dados."
            );
        } else {
            pokemonRepository.delete(pokemonParaRemover);
            return pokemonParaRemover;
        }
    }

    public Pokemon alterarPokemonPorId(Long id, Pokemon pokemon) {
        Pokemon pokemonParaAlterar = pokemonRepository.findById(id).get();
        pokemonParaAlterar.setNome(pokemon.getNome());
        pokemonParaAlterar.setTipo(pokemon.getTipo());
        pokemonRepository.save(pokemonParaAlterar);
        return pokemonParaAlterar;
    }

    public Integer buscarTodosPokemonEQuantidade() {
        Integer quantidadeDePokemonsNoBanco = buscarTodos().size();

        if (quantidadeDePokemonsNoBanco == 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Não existe nenhum Pokémon no Banco de Dados."
            );
        } else {
            return quantidadeDePokemonsNoBanco;
        }
    }
}
