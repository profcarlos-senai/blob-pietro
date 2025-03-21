package br.senai.aula.repo;

import br.senai.aula.model.Arquivo;
import br.senai.aula.model.ArquivoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    @Query(value="SELECT id, descricao, criado_em FROM arquivos", nativeQuery = true)
    List<ArquivoDTO> findAllSemArquivo();

    @Query(value="SELECT id, nome_arquivo, descricao, criado_em FROM arquivos where id=:id", nativeQuery = true)
    ArquivoDTO findByIdSemArquivo(@Param("id") Long id);
}
