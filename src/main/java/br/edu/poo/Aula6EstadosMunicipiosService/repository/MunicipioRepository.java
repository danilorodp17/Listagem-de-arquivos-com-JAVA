package br.edu.poo.Aula6EstadosMunicipiosService.repository;

import br.edu.poo.Aula6EstadosMunicipiosService.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    // Ordena os municípios por nome dentro de um estado específico
    default List<Municipio> findByEstadoIdOrderByNomeAsc(Long estadoId) {
        return findByEstado_Id(estadoId, Sort.by(Sort.Direction.ASC, "nome"));
    }

    List<Municipio> findByEstado_IdAndNomeContainingIgnoreCase(Long estadoId, String nomeParcial);

    List<Municipio> findByEstado_Id(Long estadoId, Sort nome);

    List<Municipio> findByNomeContainingIgnoreCase(String nomeParcial);
}
