package br.edu.poo.Aula6EstadosMunicipiosService.controller;

import br.edu.poo.Aula6EstadosMunicipiosService.model.Municipio;
import br.edu.poo.Aula6EstadosMunicipiosService.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/municipios")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    // Endpoint para listar todos os municípios sem filtros ou ordenação
    @GetMapping
    public List<Municipio> listarTodosMunicipios() {
        return municipioService.listarTodosMunicipios();
    }

    @GetMapping("/estado/{estadoId}")
    public List<Municipio> listarMunicipiosPorEstado(@PathVariable Long estadoId) {
        return municipioService.listarMunicipiosPorEstado(estadoId);
    }

    // Endpoint para listar todos os municípios em ordem alfabética
    @GetMapping("/ordenados")
    public List<Municipio> listarTodosMunicipiosOrdenados() {
        return municipioService.listarTodosMunicipiosOrdenados();
    }

    @GetMapping("/filtrar")
    public List<Municipio> filtrarMunicipiosPorNome(@RequestParam String nomeParcial) {
        return municipioService.filtrarMunicipiosPorNome(nomeParcial);
    }
}
