package br.edu.poo.Aula6EstadosMunicipiosService.repository;

import br.edu.poo.Aula6EstadosMunicipiosService.model.Estado;
import br.edu.poo.Aula6EstadosMunicipiosService.model.Pais;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    // Ordenar todos os estados por nome
    default List<Pais> findAllByOrderByNomeAsc() {
        return findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    // Filtrar estados pelo nome
    List<Pais> findByNomeContainingIgnoreCase(String nomeParcial);
}
