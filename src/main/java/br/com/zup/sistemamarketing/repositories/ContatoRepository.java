package br.com.zup.sistemamarketing.repositories;

import br.com.zup.sistemamarketing.models.Contato;
import org.springframework.data.repository.CrudRepository;

public interface ContatoRepository extends CrudRepository<Contato, String> {

    Iterable<Contato> findAllByProdutosNome(String nome);
    Iterable<Contato> findAllByProdutosCategoriasNome(String nome);

    boolean existsByEmail(String email);
}
