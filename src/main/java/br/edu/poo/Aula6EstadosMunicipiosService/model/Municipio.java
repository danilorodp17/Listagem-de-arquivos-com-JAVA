package br.edu.poo.Aula6EstadosMunicipiosService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Municipio {
    @Id
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    // Construtores
    public Municipio() {}

    public Municipio(Long id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}