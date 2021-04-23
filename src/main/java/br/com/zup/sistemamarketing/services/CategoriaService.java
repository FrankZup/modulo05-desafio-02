package br.com.zup.sistemamarketing.services;

import br.com.zup.sistemamarketing.exceptions.CategoriaNaoExisteException;
import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria salvarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Iterable <Categoria> pesquisarTodasCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarCategoriaPeloId(int id){
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);

        if( optionalCategoria.isPresent() ){
            return optionalCategoria.get();
        }

        throw new CategoriaNaoExisteException();
    }

    public Categoria atualizarCategoria(Categoria categoriaAntiga){

        Categoria categoriaAtual = buscarCategoriaPeloId(categoriaAntiga.getId());
        categoriaAtual.setNome(categoriaAntiga.getNome());
        return categoriaRepository.save(categoriaAntiga);
    }

    public void deletarCategoria(int id){
        Categoria categoria = buscarCategoriaPeloId(id);

        categoriaRepository.delete(categoria);
    }
}
