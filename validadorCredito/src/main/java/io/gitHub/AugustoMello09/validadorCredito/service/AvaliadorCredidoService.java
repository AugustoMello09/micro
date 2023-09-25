package io.gitHub.AugustoMello09.validadorCredito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.gitHub.AugustoMello09.validadorCredito.domain.Client;
import io.gitHub.AugustoMello09.validadorCredito.domain.SituacaoCliente;
import io.gitHub.AugustoMello09.validadorCredito.infra.clients.ClienteResourceClient;

@Service
public class AvaliadorCredidoService {
	
	@Autowired
	private ClienteResourceClient clienteResourceClient;
	
	public SituacaoCliente obterSituacaoCliente(String cpf) {
		
		ResponseEntity<Client> dadosClienteResponse = clienteResourceClient.findByCpf(cpf);
		
		return SituacaoCliente.builder()
				.cliente(dadosClienteResponse.getBody())
				.build();
	}

}
