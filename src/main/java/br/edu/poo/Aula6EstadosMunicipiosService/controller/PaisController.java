package br.edu.poo.Aula6EstadosMunicipiosService.controller;


import br.edu.poo.Aula6EstadosMunicipiosService.model.Pais;
import br.edu.poo.Aula6EstadosMunicipiosService.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping
    public List<Pais> listarPaises() {
        Iterable<Pais> pais = paisService.listarPaises();
        List<Pais> paisList = new ArrayList<>();
        pais.forEach(paisList::add);
        return paisList;
    }

    @GetMapping("/ordenados")
    public List<Pais> listarPaisesOrdenadosPorNome() {
        return paisService.listarPaisesOrdenadosPorNome();
    }

    @GetMapping("/filtrar")
    public List<Pais> filtrarPaisesPorNome(@RequestParam String nomeParcial) {
        return paisService.filtrarPaisesPorNome(nomeParcial);
    }

    @GetMapping("/{id}")
    public Pais buscarPaisPorId(@PathVariable Long id) {
        return paisService.buscarPaisPorId(id);
    }
}