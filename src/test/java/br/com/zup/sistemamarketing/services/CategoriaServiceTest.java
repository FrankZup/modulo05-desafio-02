package br.com.zup.sistemamarketing.services;

import br.com.zup.sistemamarketing.exceptions.CategoriaNaoExisteException;
import br.com.zup.sistemamarketing.exceptions.ExcecaoBasica;
import br.com.zup.sistemamarketing.exceptions.ExcluirCategoriaException;
import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.repositories.CategoriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;

import java.util.Optional;

@SpringBootTest
public class CategoriaServiceTest {
    @Autowired
    CategoriaService categoriaService;

    @MockBean
    private CategoriaRepository categoriaRepository;

    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        this.categoria = new Categoria();

        this.categoria.setId(1);
        this.categoria.setNome("Padaria");
    }

    @Test
    public void testarSalvarCategoria(){
        Mockito.when(categoriaRepository.save(Mockito.any(Categoria.class))).thenReturn(this.categoria);

        Categoria categoria = categoriaService.salvarCategoria(this.categoria);

        Assertions.assertSame(this.categoria, categoria);
        Assertions.assertEquals(this.categoria, categoria);
    }

    @Test
    public void testarBuscarCategoriaPeloIDcaminhoBom(){
        Optional<Categoria> optionalCategoria = Optional.of(this.categoria);
        Mockito.when(categoriaRepository.findById(Mockito.anyInt())).thenReturn(optionalCategoria);

        Categoria categoria = categoriaService.buscarCategoriaPeloId(10);

        Assertions.assertSame(this.categoria, categoria);
        Assertions.assertEquals(categoria.getId(),1);
    }

    @Test
    public void testarBuscarCategoriaPeloIDcaminhoRuim(){
        Optional<Categoria> optionalCategoria = Optional.empty();
        Mockito.when(categoriaRepository.findById(Mockito.anyInt())).thenReturn(optionalCategoria);

        ExcecaoBasica excecao = Assertions.assertThrows(CategoriaNaoExisteException.class, () -> {
            categoriaService.buscarCategoriaPeloId(100);
        });

        Assertions.assertEquals(404, excecao.getStatus());
        Assertions.assertEquals("Categoria não existe!", excecao.getMessage());
    }

    @Test
    public void testarAtualizarCategoriaCaminhoBom(){
        Optional<Categoria> optionalCategoria = Optional.of(this.categoria);

        Mockito.when(categoriaRepository.save(Mockito.any(Categoria.class))).thenReturn(this.categoria);
        Mockito.when(categoriaRepository.findById(Mockito.anyInt())).thenReturn(optionalCategoria);

        Categoria categoriaAtualizada = new Categoria();
        categoriaAtualizada.setId(1);
        categoriaAtualizada.setNome("Nova categoria");

        Categoria novaCategoria = categoriaService.atualizarCategoria(categoriaAtualizada);

        Assertions.assertEquals(this.categoria, novaCategoria);
    }

    @Test
    public void testarAtualizarCategoriaCaminhoRuim(){
        Optional<Categoria> optionalCategoria = Optional.empty();

        Mockito.when(categoriaRepository.save(Mockito.any(Categoria.class))).thenReturn(categoria);
        Mockito.when(categoriaRepository.findById(Mockito.anyInt())).thenReturn(optionalCategoria);

        Categoria categoriaAtualizada = new Categoria();

        categoriaAtualizada.setId(1);
        categoriaAtualizada.setNome("NovaCategoria");

        ExcecaoBasica excecao = Assertions.assertThrows(CategoriaNaoExisteException.class, () -> {
            categoriaService.atualizarCategoria(categoriaAtualizada);
        });

        Assertions.assertEquals(404, excecao.getStatus());
        Assertions.assertEquals("Categoria não existe!", excecao.getMessage());

    }
}
