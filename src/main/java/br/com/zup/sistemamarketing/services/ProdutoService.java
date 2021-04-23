package br.com.zup.sistemamarketing.services;

import br.com.zup.sistemamarketing.exceptions.ProdutoNaoExisteException;
import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.models.Produto;
import br.com.zup.sistemamarketing.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaService categoriaService) {
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
    }

    public Produto salvarProduto(Produto produto){

        produto.setCategorias(criarListaCategoria(produto.getCategorias()));
        return produtoRepository.save(produto);
    }

    public Iterable <Produto> pesquisarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarProdutoPeloId(int id){
        Optional<Produto> optionalProduto = produtoRepository.findById(id);

        if( optionalProduto.isPresent() ){
            return optionalProduto.get();
        }

        throw new ProdutoNaoExisteException();
    }

    public Produto atualizarProduto(Produto produtoAntigo){

        Produto produtoAtualizado = buscarProdutoPeloId(produtoAntigo.getId());

        produtoAtualizado.setNome(produtoAntigo.getNome());
        produtoAtualizado.setCategorias(criarListaCategoria(produtoAntigo.getCategorias()));

        return produtoRepository.save(produtoAtualizado);
    }

    public void deletarProduto(int id){
        Produto produto = buscarProdutoPeloId(id);
        produtoRepository.delete(produto);
    }

    private List<Categoria> criarListaCategoria(List<Categoria> categorias) {
        List<Categoria> adcionarCategoriaAoProduto = new ArrayList<>();

        for (Categoria categoria : categorias) {
            adcionarCategoriaAoProduto.add(
                    categoriaService.buscarCategoriaPeloId(categoria.getId())
            );
        }

        return adcionarCategoriaAoProduto;
    }
}
