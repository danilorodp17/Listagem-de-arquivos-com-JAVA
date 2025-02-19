package br.edu.poo.Aula6EstadosMunicipiosService.service;

import br.edu.poo.Aula6EstadosMunicipiosService.model.Estado;
import br.edu.poo.Aula6EstadosMunicipiosService.model.Municipio;
import br.edu.poo.Aula6EstadosMunicipiosService.repository.EstadoRepository;
import br.edu.poo.Aula6EstadosMunicipiosService.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Municipio> listarTodosMunicipios() {
        return municipioRepository.findAll();
    }

    // Lista os municípios de um estado específico em ordem alfabética
    public List<Municipio> listarMunicipiosPorEstado(Long estadoId) {
        Estado estado = estadoRepository.findById(estadoId).orElse(null);
        return estado != null ? municipioRepository.findByEstadoIdOrderByNomeAsc(estadoId) : new ArrayList<>();
    }

    // Lista todos os municípios, ordenados por nome
    public List<Municipio> listarTodosMunicipiosOrdenados() {
        return municipioRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    // Filtra os municípios por parte do nome dentro de um estado específico
    public List<Municipio> filtrarMunicipiosPorNome(Long estadoId, String nomeParcial) {
        return municipioRepository.findByEstado_IdAndNomeContainingIgnoreCase(estadoId, nomeParcial);
    }

    public List<Municipio> filtrarMunicipiosPorNome(String nomeParcial) {
        return municipioRepository.findByNomeContainingIgnoreCase(nomeParcial);
    }
}
