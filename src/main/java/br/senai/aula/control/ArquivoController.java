package br.senai.aula.control;

import br.senai.aula.model.Arquivo;
import br.senai.aula.model.ArquivoDTO;
import br.senai.aula.repo.ArquivoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/arquivos")
public class ArquivoController {
    private final ArquivoRepository arquivoRepository;

    public ArquivoController(ArquivoRepository arquivoRepository) {
        this.arquivoRepository = arquivoRepository;
    }

    // Fazer upload de um arquivo
    @PostMapping("/upload")
    public ResponseEntity<String> uploadArquivo(@RequestParam("arquivo") MultipartFile file,
                                                @RequestParam("descricao") String descricao) {
        try {
            Arquivo arquivo = new Arquivo();
            arquivo.setDescricao(descricao);
            arquivo.setDadosArquivo(file.getBytes());

            arquivoRepository.save(arquivo);
            return ResponseEntity.ok("Arquivo enviado com sucesso! ID: " + arquivo.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha no envio do arquivo");
        }
    }

    // Obter um arquivo pelo ID
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> getDownloadArquivo(@PathVariable Long id) {
        Optional<Arquivo> arquivo = arquivoRepository.findById(id);
        if (arquivo.isPresent()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=arquivo")
                    .body(arquivo.get().getDadosArquivo());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArquivoDTO> getArquivo(@PathVariable Long id){
        var arquivo = arquivoRepository.findByIdSemArquivo(id);
        if (arquivo != null) {
            return ResponseEntity.ok(arquivo); // Return the found arquivo with 200 OK status
        } else {
            return ResponseEntity.notFound().build(); // Return HTTP 404 Not Found
        }
    }

    // Listar todos os arquivos
    @GetMapping
    public List<ArquivoDTO> listarArquivos() {
            return arquivoRepository.findAllSemArquivo();
    }
}
