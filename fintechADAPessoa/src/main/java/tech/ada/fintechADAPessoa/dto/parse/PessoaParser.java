package tech.ada.fintechADAPessoa.dto.parse;

import tech.ada.fintechADAPessoa.dto.PessoaDTO;
import tech.ada.fintechADAPessoa.dto.PessoaFisicaDTO;
import tech.ada.fintechADAPessoa.dto.PessoaJuridicaDTO;
import tech.ada.fintechADAPessoa.model.Pessoa;
import tech.ada.fintechADAPessoa.model.PessoaFisica;
import tech.ada.fintechADAPessoa.model.PessoaJuridica;

public class PessoaParser {

    public static PessoaDTO toPessoaDTO(Pessoa pessoa) {
        if (pessoa instanceof PessoaFisica) {
            PessoaFisica pf = (PessoaFisica) pessoa;
            PessoaFisicaDTO pessoaDTO = new PessoaFisicaDTO();
            pessoaDTO.setCpf(pf.getCpf());
            pessoaDTO.setRg(pf.getRg());
            // outros campos específicos de PessoaFisica
            return pessoaDTO;
        } else if (pessoa instanceof PessoaJuridica) {
            PessoaJuridica pj = (PessoaJuridica) pessoa;
            PessoaJuridicaDTO pessoaDTO = new PessoaJuridicaDTO();
            pessoaDTO.setCnpj(pj.getCnpj());
            pessoaDTO.setInscricaoEstadual(pj.getInscricaoEstadual());
            // outros campos específicos de PessoaJuridica
            return pessoaDTO;
        }
        return null;
    }

    public static PessoaFisicaDTO toPessoaFisicaDTO(PessoaFisica pessoaFisica) {
        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
        pessoaFisicaDTO.setId(pessoaFisica.getId());
        pessoaFisicaDTO.setNome(pessoaFisica.getNome());
        pessoaFisicaDTO.setEmail(pessoaFisica.getEmail());
        // outros campos específicos de Pessoa Física
        return pessoaFisicaDTO;
    }

    public static PessoaJuridicaDTO toPessoaJuridicaDTO(PessoaJuridica pessoaJuridica) {
        PessoaJuridicaDTO pessoaJuridicaDTO = new PessoaJuridicaDTO();
        pessoaJuridicaDTO.setId(pessoaJuridica.getId());
        pessoaJuridicaDTO.setNome(pessoaJuridica.getNome());
        pessoaJuridicaDTO.setEmail(pessoaJuridica.getEmail());
        // outros campos específicos de Pessoa Jurídica
        return pessoaJuridicaDTO;
    }

    public static Pessoa toPessoaEntity(PessoaDTO pessoaDTO) {
        Pessoa pessoa;

        // Dependendo de alguma lógica, determine se é PessoaFisica ou PessoaJuridica
        if (pessoaDTO instanceof PessoaFisicaDTO) {
            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setNome(pessoaDTO.getNome());
            pessoaFisica.setEmail(pessoaDTO.getEmail());
            // outros campos comuns para PessoaFisica
            pessoa = pessoaFisica;
        } else {
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setNome(pessoaDTO.getNome());
            pessoaJuridica.setEmail(pessoaDTO.getEmail());
            // outros campos comuns para PessoaJuridica
            pessoa = pessoaJuridica;
        }

        return pessoa;
    }


   
}

