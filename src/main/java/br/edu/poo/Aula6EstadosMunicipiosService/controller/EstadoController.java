package br.edu.poo.Aula6EstadosMunicipiosService.controller;

import br.edu.poo.Aula6EstadosMunicipiosService.model.Estado;
import br.edu.poo.Aula6EstadosMunicipiosService.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listarEstados() {
        Iterable<Estado> estados = estadoService.listarEstados();
        List<Estado> estadoList = new ArrayList<>();
        estados.forEach(estadoList::add);
        return estadoList;
    }

    @GetMapping("/ordenados")
    public List<Estado> listarEstadosOrdenadosPorNome() {
        return estadoService.listarEstadosOrdenadosPorNome();
    }

    @GetMapping("/filtrar")
    public List<Estado> filtrarEstadosPorNome(@RequestParam String nomeParcial) {
        return estadoService.filtrarEstadosPorNome(nomeParcial);
    }

    @GetMapping("/{id}")
    public Estado buscarEstadoPorId(@PathVariable Long id) {
        return estadoService.buscarEstadoPorId(id);
    }

}
