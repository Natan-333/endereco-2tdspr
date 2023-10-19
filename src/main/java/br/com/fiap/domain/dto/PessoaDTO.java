package br.com.fiap.domain.dto;

import br.com.fiap.Main;
import br.com.fiap.domain.entity.Endereco;
import br.com.fiap.infra.security.entity.Pessoa;
import br.com.fiap.infra.security.entity.PessoaFisica;
import br.com.fiap.infra.security.service.PessoaFisicaService;
import br.com.fiap.infra.security.service.PessoaJuridicaService;

import java.util.Objects;

public record PessoaDTO(Long id, String nome, String email) {

    static PessoaFisicaService pfService = PessoaFisicaService.of(Main.PERSISTENCE_UNIT);
    static PessoaJuridicaService pjService = PessoaJuridicaService.of(Main.PERSISTENCE_UNIT);

    public PessoaDTO(Long id, String nome, String email, String s) {
    }

    public static Pessoa of(PessoaDTO p){
        if(Objects.isNull(p)|| Objects.isNull(p.id())) return null;
        Pessoa pessoa = pfService.findById(p.id);
        if(Objects.isNull(pessoa)){
            pessoa =pfService.findById(p.id);
        }

        if(Objects.isNull(pessoa)) return null;
        return pessoa;



    }
    public static PessoaDTO of(Pessoa p) {
        if(Objects.isNull(p)|| Objects.isNull(p.getId())) return null;
        PessoaDTO dto = new PessoaDTO(p.getId(), p.getNome(), p.getEmail(), p instanceof PessoaFisica ? "PF" : "PJ");
        return dto;

    }

}
