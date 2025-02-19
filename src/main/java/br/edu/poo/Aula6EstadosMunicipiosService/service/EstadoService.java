package br.edu.poo.Aula6EstadosMunicipiosService.service;

import br.edu.poo.Aula6EstadosMunicipiosService.model.Estado;
import br.edu.poo.Aula6EstadosMunicipiosService.repository.EstadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    // Lista todos os estados
    public Iterable<Estado> listarEstados() {
        return estadoRepository.findAll();
    }

    // Lista todos os estados em ordem alfabética
    public List<Estado> listarEstadosOrdenadosPorNome() {
        return estadoRepository.findAllByOrderByNomeAsc();
    }

    // Filtra estados pelo nome
    public List<Estado> filtrarEstadosPorNome(String nomeParcial) {
        return estadoRepository.findByNomeContainingIgnoreCase(nomeParcial);
    }

    // Busca um estado pelo ID
    public Estado buscarEstadoPorId(Long id) {
        return estadoRepository.findById(id).orElse(null);
    }

    // Importa os dados de estados do CSV ao iniciar a aplicação
    @PostConstruct
    public void importarEstadosCSV() {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("estados.csv").getInputStream(), StandardCharsets.UTF_8))) {
            String linha;
            int lineNumber = 0;

            while ((linha = reader.readLine()) != null) {
                lineNumber++;
                try {
                    String[] dados = linha.split(",");

                    if (dados.length < 3) {
                        logger.warn("Linha {} inválida no CSV: {}", lineNumber, linha);
                        continue;
                    }

                    Long id = Long.parseLong(dados[0].trim());
                    String nome = dados[1].trim();
                    String sigla = dados[2].trim();

                    if (!estadoRepository.existsById(id)) {
                        estadoRepository.save(new Estado(id, nome, sigla));
                    } else {
                        logger.info("Estado com ID {} já existe. Ignorando duplicata.", id);
                    }
                } catch (NumberFormatException e) {
                    logger.error("Erro ao converter ID para número na linha {}: {}", lineNumber, linha, e);
                } catch (Exception e) {
                    logger.error("Erro ao processar linha {}: {}", lineNumber, linha, e);
                }
            }
        } catch (Exception e) {
            logger.error("Erro ao ler o arquivo CSV de estados.", e);
        }
    }
}