package br.edu.poo.Aula6EstadosMunicipiosService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Estado {
    @Id
    private Long id;
    private String nome;
    private String sigla;

    // Construtores
    public Estado() {}

    public Estado(Long id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}