package br.edu.poo.Aula6EstadosMunicipiosService.service;

import br.edu.poo.Aula6EstadosMunicipiosService.model.Pais;
import br.edu.poo.Aula6EstadosMunicipiosService.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisService {
    @Autowired
    private PaisRepository paisRepository;

    // Lista todos os estados
    public Iterable<Pais> listarPaises() {
        return paisRepository.findAll();
    }

    // Lista todos os estados em ordem alfabética
    public List<Pais> listarPaisesOrdenadosPorNome() {
        return paisRepository.findAllByOrderByNomeAsc();
    }

    // Filtra estados pelo nome
    public List<Pais> filtrarPaisesPorNome(String nomeParcial) {
        return paisRepository.findByNomeContainingIgnoreCase(nomeParcial);
    }

    // Busca um estado pelo ID
    public Pais buscarPaisPorId(Long id) {
        return paisRepository.findById(id).orElse(null);
    }
}
