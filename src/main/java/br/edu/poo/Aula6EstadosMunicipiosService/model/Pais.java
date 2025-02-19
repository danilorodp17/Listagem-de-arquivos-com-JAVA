package br.edu.poo.Aula6EstadosMunicipiosService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Pais {
    @Id
    private Long id;
    private String nome;
    private String sigla;

    // Construtores
    public Pais() {}

    public Pais(Long id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }
}
