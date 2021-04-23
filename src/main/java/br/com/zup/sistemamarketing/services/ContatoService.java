package br.com.zup.sistemamarketing.services;

import br.com.zup.sistemamarketing.DTOs.contato.filtro.FiltroContatoPorCategoria;
import br.com.zup.sistemamarketing.DTOs.contato.filtro.FiltroContatoPorProduto;
import br.com.zup.sistemamarketing.exceptions.ContatoNaoExisteException;
import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.models.Produto;
import br.com.zup.sistemamarketing.repositories.ContatoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final ProdutoService produtoService;

    public ContatoService(ContatoRepository contatoRepository, ProdutoService produtoService) {
        this.contatoRepository = contatoRepository;
        this.produtoService = produtoService;
    }

    public Contato salvarContato(Contato contato){

        if (!contatoRepository.existsByEmail(contato.getEmail())) {
            contato.setProdutos(verificarSeProdutoExiste(contato.getProdutos()));
            return contatoRepository.save(contato);
        }

        return adicionarNovoProdutoNoContatoExistente(contato);

    }

    public Iterable <Contato> pesquisarTodosContatos() {
        return contatoRepository.findAll();
    }

    public Contato buscarContatoPeloEmail(String email){
        Optional<Contato> optionalContato = contatoRepository.findById(email);

        if( optionalContato.isPresent() ){
            return optionalContato.get();
        }

        throw new ContatoNaoExisteException();
    }

    public Contato atualizarContato(Contato contato){
        Contato contatoAtual = buscarContatoPeloEmail(contato.getEmail());

        contatoAtual.setNomeCompleto(contato.getNomeCompleto());
        contatoAtual.setEmail(contato.getEmail());
        contatoAtual.setTelefone(contato.getTelefone());

        atualizarListaProdutosDoContato(contatoAtual, contato);

        return contatoRepository.save(contatoAtual);
    }

    private void atualizarListaProdutosDoContato(Contato contatoAtual,
                                                 Contato contatoAtualizado) {
        List<Produto> listaProdutos = verificarSeProdutoExiste(contatoAtualizado.getProdutos());
        listaProdutos.addAll(contatoAtual.getProdutos());
        contatoAtual.setProdutos(listaProdutos);
    }

    public void deletarContato(String email){
        Contato contato = buscarContatoPeloEmail(email);
        contatoRepository.delete(contato);
    }

    private Contato adicionarNovoProdutoNoContatoExistente(Contato contatoAtualizado){
        Contato contatoExistente = buscarContatoPeloEmail(contatoAtualizado.getEmail());

        List<Produto> produtos = contatoExistente.getProdutos();

        for (Produto produto : contatoAtualizado.getProdutos()){
            produtos.add(
                    produtoService.buscarProdutoPeloId(produto.getId())
            );
        }

        return contatoRepository.save(contatoExistente);
    }


    private List<Produto> verificarSeProdutoExiste(List<Produto> produtos) {
        List<Produto> adicionarProdutoAoContato = new ArrayList<>();

        for (Produto produto : produtos) {
            adicionarProdutoAoContato.add(produtoService.buscarProdutoPeloId(produto.getId()));
        }

        return adicionarProdutoAoContato;
    }

    public Iterable<Contato> pesquisarContatoPorProduto(FiltroContatoPorProduto filtro) {

        if (filtro == null) {
            return contatoRepository.findAll();
        }

        return contatoRepository.findAllByProdutosNome(filtro.getNome());
    }

    public Iterable<Contato> pesquisarContatoPorCategoria(FiltroContatoPorCategoria filtro) {

        if (filtro == null) {
            return contatoRepository.findAll();
        }

        return contatoRepository.findAllByProdutosCategoriasNome(filtro.getNome());
    }
}
