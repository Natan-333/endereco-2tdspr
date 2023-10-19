package br.com.fiap.domain.dto;

import br.com.fiap.domain.entity.Endereco;
import br.com.fiap.domain.service.EnderecoService;

import java.util.Objects;

public record EnderecoDTO(PessoaDTO pessoa, String cep, String numero, String complemento) {

    static  private EnderecoService service = new EnderecoService();
    public static Endereco of(EnderecoDTO dto){
        var pessoa = PessoaDTO.of(dto.pessoa);

        if(Objects.isNull(pessoa)) return null;

        var endereco = service.findByCEP(dto.cep);

        if(Objects.isNull(endereco)) return null;

        endereco.setNumero(dto.numero);

        endereco.setNumero(dto.complemento);

        endereco.setPessoa(pessoa);

        return endereco;

    }


    public static  EnderecoDTO of(Endereco endereco){

        if(Objects.isNull(endereco)) return null;

        var pessoa =PessoaDTO.of(endereco.getPessoa());

        if(Objects.isNull(pessoa)) return null;

        var dto = new EnderecoDTO(pessoa, endereco, endereco.getCep(), endereco.getNumero() endereco.getComplemento());

        return dto;
    }

}
