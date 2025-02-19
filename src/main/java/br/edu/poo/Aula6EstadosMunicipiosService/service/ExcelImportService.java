package br.edu.poo.Aula6EstadosMunicipiosService.service;

import br.edu.poo.Aula6EstadosMunicipiosService.model.Estado;
import br.edu.poo.Aula6EstadosMunicipiosService.model.Municipio;
import br.edu.poo.Aula6EstadosMunicipiosService.model.Pais;
import br.edu.poo.Aula6EstadosMunicipiosService.repository.EstadoRepository;
import br.edu.poo.Aula6EstadosMunicipiosService.repository.MunicipioRepository;
import br.edu.poo.Aula6EstadosMunicipiosService.repository.PaisRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;

@Service
public class ExcelImportService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private PaisRepository paisRepository;

    @PostConstruct
    public void importarDadosExcel() {
        importarEstados();
        importarMunicipios();
        importarPaises();
    }

    private void importarEstados() {
        try (InputStream is = new FileInputStream(Paths.get("src/main/java/br/edu/poo/Aula6EstadosMunicipiosService/util/estados.xlsx").toFile());
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Pula o cabeçalho

                Long id = (long) row.getCell(0).getNumericCellValue();
                String nome = row.getCell(1).getStringCellValue();
                String sigla = row.getCell(2).getStringCellValue();

                if (!estadoRepository.existsById(id)) {
                    estadoRepository.save(new Estado(id, nome, sigla));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void importarMunicipios() {
        try (InputStream is = new FileInputStream(Paths.get("src/main/java/br/edu/poo/Aula6EstadosMunicipiosService/util/municipios.xlsx").toFile());
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Pula o cabeçalho

                Long estadoId = (long) row.getCell(0).getNumericCellValue();
                Long id = (long) row.getCell(1).getNumericCellValue();
                String nome = row.getCell(2).getStringCellValue();

                Estado estado = estadoRepository.findById(estadoId).orElse(null);
                if (estado != null && !municipioRepository.existsById(id)) {
                    municipioRepository.save(new Municipio(id, nome, estado));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void importarPaises() {
        try (InputStream is = new FileInputStream(Paths.get("src/main/java/br/edu/poo/Aula6EstadosMunicipiosService/util/paises.xlsx").toFile());
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Pula o cabeçalho

                Long id = (long) row.getCell(0).getNumericCellValue();
                String nome = row.getCell(1).getStringCellValue();
                String sigla = row.getCell(2).getStringCellValue();

                if (!paisRepository.existsById(id)) {
                    paisRepository.save(new Pais(id, nome, sigla));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
