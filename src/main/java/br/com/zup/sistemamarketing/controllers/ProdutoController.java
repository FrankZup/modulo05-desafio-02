package br.com.zup.sistemamarketing.controllers;

import br.com.zup.sistemamarketing.DTOs.produto.entrada.AtualizaProdutoDTO;
import br.com.zup.sistemamarketing.DTOs.produto.entrada.CadastraProdutoDTO;
import br.com.zup.sistemamarketing.DTOs.produto.saida.ProdutoDTO;
import br.com.zup.sistemamarketing.models.Produto;
import br.com.zup.sistemamarketing.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO cadastrarProduto(@RequestBody @Valid CadastraProdutoDTO produtoDTO) {

        Produto produto = produtoDTO.converterDTOparaProduto();
        Produto objetoProduto = produtoService.salvarProduto(produto);

        return ProdutoDTO.converterProdutoParaDTO(objetoProduto);
    }

    @GetMapping
    public Iterable <Produto> listarTodosProdutos() {
        return produtoService.pesquisarTodosProdutos();
    }


    @GetMapping("{id}/")
    public Produto buscarProdutoPeloId(@PathVariable int id){
        return produtoService.buscarProdutoPeloId(id);
    }

    @PutMapping("{id}/")
    public Produto atualizarProduto(@PathVariable int id,
                                    @RequestBody @Valid AtualizaProdutoDTO atualizaProdutoDTO){
        Produto produto = atualizaProdutoDTO.atualizarDTOproduto(id);
        return produtoService.atualizarProduto(produto);
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable int id){
        produtoService.deletarProduto(id);
    }
}
