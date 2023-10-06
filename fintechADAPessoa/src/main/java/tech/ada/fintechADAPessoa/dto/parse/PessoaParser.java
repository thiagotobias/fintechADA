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

		// Dependendo de alguma lógica, determine se é PessoaFisica ou PessoaJuridica
		if (pessoaDTO instanceof PessoaFisicaDTO) {
			PessoaFisicaDTO pessoaFisicaDTO = (PessoaFisicaDTO) pessoaDTO;

			PessoaFisica pessoaFisica = new PessoaFisica();
			pessoaFisica.setNome(pessoaFisicaDTO.getNome());
			pessoaFisica.setEmail(pessoaFisicaDTO.getEmail());
			pessoaFisica.setSite(pessoaFisicaDTO.getSite());
			pessoaFisica.setRua(pessoaFisicaDTO.getRua());
			pessoaFisica.setNumero(pessoaFisicaDTO.getNumero());
			pessoaFisica.setComplemento(pessoaFisicaDTO.getComplemento());
			pessoaFisica.setBairro(pessoaFisicaDTO.getBairro());
			pessoaFisica.setCidade(pessoaFisicaDTO.getCidade());
			pessoaFisica.setCep(pessoaFisicaDTO.getCep());
			pessoaFisica.setEstado(pessoaFisicaDTO.getEstado());
			pessoaFisica.setCpf(pessoaFisicaDTO.getCpf());
			pessoaFisica.setRg(pessoaFisica.getRg());
			pessoaFisica.setDataRg(pessoaFisicaDTO.getDataRg());
			pessoaFisica.setOrgaoRg(pessoaFisicaDTO.getOrgaoRg());
			pessoaFisica.setSexo(pessoaFisicaDTO.getSexo());
			pessoaFisica.setRaca(pessoaFisicaDTO.getRaca());
			pessoaFisica.setNaturalidade(pessoaFisicaDTO.getNaturalidade());
			pessoaFisica.setNacionalidade(pessoaFisicaDTO.getNaturalidade());
			pessoaFisica.setNomePai(pessoaFisicaDTO.getNomePai());
			pessoaFisica.setNomeMae(pessoaFisicaDTO.getNomeMae());
			// outros campos comuns para PessoaFisica
			return pessoaFisica;
		} else {
			PessoaJuridicaDTO pessoaJuridicaDTO = (PessoaJuridicaDTO) pessoaDTO;

			PessoaJuridica pessoaJuridica = new PessoaJuridica();
			pessoaJuridica.setNome(pessoaJuridicaDTO.getNome());
			pessoaJuridica.setEmail(pessoaJuridicaDTO.getEmail());
			pessoaJuridica.setSite(pessoaJuridicaDTO.getSite());
			pessoaJuridica.setRua(pessoaJuridicaDTO.getRua());
			pessoaJuridica.setNumero(pessoaJuridicaDTO.getNumero());
			pessoaJuridica.setComplemento(pessoaJuridicaDTO.getComplemento());
			pessoaJuridica.setBairro(pessoaJuridicaDTO.getBairro());
			pessoaJuridica.setCidade(pessoaJuridicaDTO.getCidade());
			pessoaJuridica.setCep(pessoaJuridicaDTO.getCep());
			pessoaJuridica.setEstado(pessoaJuridicaDTO.getEstado());
			pessoaJuridica.setCnpj(pessoaJuridicaDTO.getCnpj());
			pessoaJuridica.setInscricaoEstadual(pessoaJuridicaDTO.getInscricaoEstadual());
			pessoaJuridica.setInscricaoMunicipal(pessoaJuridicaDTO.getInscricaoMunicipal());
			pessoaJuridica.setNomeFantasia(pessoaJuridicaDTO.getNomeFantasia());
			// outros campos comuns para PessoaJuridica
			return pessoaJuridica;
		}

	}


   
}

