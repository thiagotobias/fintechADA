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
            pessoaDTO.setId(pf.getId());
            pessoaDTO.setCpf(pf.getCpf());
            pessoaDTO.setRg(pf.getRg());
            pessoaDTO.setNome(pf.getNome());
            pessoaDTO.setEmail(pf.getEmail());
            pessoaDTO.setSite(pf.getSite());
            pessoaDTO.setRua(pf.getRua());
            pessoaDTO.setNumero(pf.getNumero());
            pessoaDTO.setComplemento(pf.getComplemento());
            pessoaDTO.setBairro(pf.getBairro());
            pessoaDTO.setCidade(pf.getCidade());
            pessoaDTO.setCep(pf.getCep());
            pessoaDTO.setEstado(pf.getEstado());
            pessoaDTO.setCpf(pf.getCpf());
            pessoaDTO.setDataRg(pf.getDataRg());
            pessoaDTO.setOrgaoRg(pf.getOrgaoRg());
            pessoaDTO.setSexo(pf.getSexo());
            pessoaDTO.setRaca(pf.getRaca());
            pessoaDTO.setNaturalidade(pf.getNaturalidade());
            pessoaDTO.setNacionalidade(pf.getNaturalidade());
            pessoaDTO.setNomePai(pf.getNomePai());
            pessoaDTO.setNomeMae(pf.getNomeMae());
            // outros campos específicos de PessoaFisica
            return pessoaDTO;
        } else if (pessoa instanceof PessoaJuridica) {
            PessoaJuridica pj = (PessoaJuridica) pessoa;
            PessoaJuridicaDTO pessoaDTO = new PessoaJuridicaDTO();
            pessoaDTO.setId(pj.getId());
            pessoaDTO.setCnpj(pj.getCnpj());
            pessoaDTO.setInscricaoEstadual(pj.getInscricaoEstadual());
            pessoaDTO.setNome(pj.getNome());
            pessoaDTO.setEmail(pj.getEmail());
    		pessoaDTO.setSite(pj.getSite());
    		pessoaDTO.setNumero(pj.getNumero());
    		pessoaDTO.setComplemento(pj.getComplemento());
    		pessoaDTO.setBairro(pj.getBairro());
    		pessoaDTO.setCidade(pj.getCidade());
    		pessoaDTO.setCep(pj.getCep());
    		pessoaDTO.setEstado(pj.getEstado());
    		pessoaDTO.setCnpj(pj.getCnpj());
    		pessoaDTO.setInscricaoEstadual(pj.getInscricaoEstadual());
    		pessoaDTO.setInscricaoMunicipal(pj.getInscricaoMunicipal());
    		pessoaDTO.setNomeFantasia(pj.getNomeFantasia());
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
        pessoaFisicaDTO.setSite(pessoaFisica.getSite());
        pessoaFisicaDTO.setRua(pessoaFisica.getRua());
        pessoaFisicaDTO.setNumero(pessoaFisica.getNumero());
        pessoaFisicaDTO.setComplemento(pessoaFisica.getComplemento());
        pessoaFisicaDTO.setBairro(pessoaFisica.getBairro());
        pessoaFisicaDTO.setCidade(pessoaFisica.getCidade());
        pessoaFisicaDTO.setCep(pessoaFisica.getCep());
        pessoaFisicaDTO.setEstado(pessoaFisica.getEstado());
        pessoaFisicaDTO.setCpf(pessoaFisica.getCpf());
        pessoaFisicaDTO.setRg(pessoaFisica.getRg());
        pessoaFisicaDTO.setDataRg(pessoaFisica.getDataRg());
        pessoaFisicaDTO.setOrgaoRg(pessoaFisica.getOrgaoRg());
        pessoaFisicaDTO.setSexo(pessoaFisica.getSexo());
        pessoaFisicaDTO.setRaca(pessoaFisica.getRaca());
		pessoaFisicaDTO.setNaturalidade(pessoaFisica.getNaturalidade());
		pessoaFisicaDTO.setNacionalidade(pessoaFisica.getNaturalidade());
		pessoaFisicaDTO.setNomePai(pessoaFisica.getNomePai());
		pessoaFisicaDTO.setNomeMae(pessoaFisica.getNomeMae());
        return pessoaFisicaDTO;
    }

	public static PessoaJuridicaDTO toPessoaJuridicaDTO(PessoaJuridica pessoaJuridica) {
		PessoaJuridicaDTO pessoaJuridicaDTO = new PessoaJuridicaDTO();
		pessoaJuridicaDTO.setId(pessoaJuridica.getId());
		pessoaJuridicaDTO.setNome(pessoaJuridica.getNome());
		pessoaJuridicaDTO.setEmail(pessoaJuridica.getEmail());
		pessoaJuridicaDTO.setSite(pessoaJuridica.getSite());
		pessoaJuridicaDTO.setRua(pessoaJuridica.getRua());
		pessoaJuridicaDTO.setNumero(pessoaJuridica.getNumero());
		pessoaJuridicaDTO.setComplemento(pessoaJuridica.getComplemento());
		pessoaJuridicaDTO.setBairro(pessoaJuridica.getBairro());
		pessoaJuridicaDTO.setCidade(pessoaJuridica.getCidade());
		pessoaJuridicaDTO.setCep(pessoaJuridica.getCep());
		pessoaJuridicaDTO.setEstado(pessoaJuridica.getEstado());
		pessoaJuridicaDTO.setCnpj(pessoaJuridica.getCnpj());
		pessoaJuridicaDTO.setInscricaoEstadual(pessoaJuridica.getInscricaoEstadual());
		pessoaJuridicaDTO.setInscricaoMunicipal(pessoaJuridica.getInscricaoMunicipal());
		pessoaJuridicaDTO.setNomeFantasia(pessoaJuridica.getNomeFantasia());
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
			pessoaFisica.setRg(pessoaFisicaDTO.getRg());
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

