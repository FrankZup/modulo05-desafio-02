package br.com.zup.sistemamarketing.controllers;

import br.com.zup.sistemamarketing.DTOs.contato.entrada.AtualizaContatoDTO;
import br.com.zup.sistemamarketing.DTOs.contato.entrada.CadastraContatoDTO;
import br.com.zup.sistemamarketing.DTOs.contato.filtro.FiltroContatoPorCategoria;
import br.com.zup.sistemamarketing.DTOs.contato.filtro.FiltroContatoPorProduto;
import br.com.zup.sistemamarketing.DTOs.contato.saida.ContatoDTO;
import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    @Autowired
    ContatoService contatoService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoDTO cadastrarContato(@RequestBody @Valid CadastraContatoDTO contatoDTO) {
        Contato contato = contatoDTO.converterDTOparaContato();
        Contato objetoContato = contatoService.salvarContato(contato);

        return ContatoDTO.converterContatoParaDTO(objetoContato);
    }

    @GetMapping
    public Iterable <Contato> listarTodosContatos() {
        return contatoService.pesquisarTodosContatos();
    }

    @GetMapping("{email}/")
    public Contato buscarContatoPeloEmail(@PathVariable String email){
        return contatoService.buscarContatoPeloEmail(email);
    }

    @PutMapping("{email}/")
    public Contato atualizarContato(@PathVariable String email,
                                    @RequestBody @Valid AtualizaContatoDTO atualizaContatoDTO){
        Contato contato = atualizaContatoDTO.atualizarDTOcontato(email);
        return contatoService.atualizarContato(contato);
    }

    @DeleteMapping("{email}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarContato(@PathVariable String email){
        contatoService.deletarContato(email);
    }

    @GetMapping("/produtos")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ContatoDTO> pesquisarContatoPorProduto(@ModelAttribute FiltroContatoPorProduto filtro) {
        Iterable<Contato> contatos = contatoService.pesquisarContatoPorProduto(filtro);
        return ContatoDTO.converterIterableDeContatosParaDTO(contatos);
    }

    @GetMapping("/categorias")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ContatoDTO> pesquisarContatoPorCategoria(@ModelAttribute FiltroContatoPorCategoria filtro) {
        Iterable<Contato> contatos = contatoService.pesquisarContatoPorCategoria(filtro);
        return ContatoDTO.converterIterableDeContatosParaDTO(contatos);
    }
}

