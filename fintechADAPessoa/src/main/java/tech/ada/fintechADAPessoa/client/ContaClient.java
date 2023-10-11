package tech.ada.fintechADAPessoa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("fintechADAConta")
public interface ContaClient {
	
	@PostMapping("/pessoas")
	PessoaReplicaDTO create(@RequestBody PessoaReplicaDTO pessoaReplica);

}
