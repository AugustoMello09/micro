package io.gitHub.AugustoMello09.validadorCredito.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.gitHub.AugustoMello09.validadorCredito.domain.Client;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteResourceClient {
	

	@GetMapping(params = "cpf")
	public ResponseEntity<Client> findByCpf(@RequestParam("cpf") String cpf);
	
	

}
