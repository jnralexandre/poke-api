package br.org.fundatec.pokeapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Pokemon_TB")
@Data
public class Pokemon {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String nome;
    @Column
    private String tipo;
    @Column
    private Double altura;
    @Column
    private Double peso;
    @Column
    private Integer level;

}
