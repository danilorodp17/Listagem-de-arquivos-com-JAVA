package br.edu.poo.Aula6EstadosMunicipiosService.repository;

import br.edu.poo.Aula6EstadosMunicipiosService.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    // Ordenar todos os estados por nome
    default List<Estado> findAllByOrderByNomeAsc() {
        return findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    // Filtrar estados pelo nome (caso insensível)
    List<Estado> findByNomeContainingIgnoreCase(String nomeParcial);
}
