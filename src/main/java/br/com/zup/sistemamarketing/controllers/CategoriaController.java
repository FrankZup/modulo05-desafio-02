package br.com.zup.sistemamarketing.controllers;

import br.com.zup.sistemamarketing.DTOs.categoria.entrada.AtualizaCategoriaDTO;
import br.com.zup.sistemamarketing.DTOs.categoria.entrada.CadastraCategoriaDTO;
import br.com.zup.sistemamarketing.DTOs.categoria.saida.CategoriaDTO;
import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    public CategoriaService categoriaService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDTO cadastrarCategoria(@RequestBody @Valid CadastraCategoriaDTO categoriaDTO) {

        Categoria categoria = categoriaDTO.converterDTOparaCategoria();
        Categoria objetoCategoria = categoriaService.salvarCategoria(categoria);

        return CategoriaDTO.converterCategoriaParaDTO(objetoCategoria);
    }

    @GetMapping
    public Iterable <Categoria> listarTodasCategorias() {
        return categoriaService.pesquisarTodasCategorias();
    }

    @GetMapping("{id}/")
    public Categoria buscarCategoriaPeloId(@PathVariable int id){
        return categoriaService.buscarCategoriaPeloId(id);
    }

    @PutMapping("{id}/")
    public Categoria atualizarCategoria(@PathVariable int id,
                                        @RequestBody @Valid AtualizaCategoriaDTO atualizaCategoriaDTO){

        Categoria categoria = atualizaCategoriaDTO.atualizarDTOcategoria(id);
        return categoriaService.atualizarCategoria(categoria);
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCategoria(@PathVariable int id){
        categoriaService.deletarCategoria(id);
    }
}
