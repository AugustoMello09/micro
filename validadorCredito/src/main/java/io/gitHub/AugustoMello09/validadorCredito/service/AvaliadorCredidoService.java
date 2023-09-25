package io.gitHub.AugustoMello09.validadorCredito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.gitHub.AugustoMello09.validadorCredito.domain.CartoesCliente;
import io.gitHub.AugustoMello09.validadorCredito.domain.Client;
import io.gitHub.AugustoMello09.validadorCredito.domain.SituacaoCliente;
import io.gitHub.AugustoMello09.validadorCredito.infra.clients.CartoesResourceClient;
import io.gitHub.AugustoMello09.validadorCredito.infra.clients.ClienteResourceClient;

@Service
public class AvaliadorCredidoService {
	
	@Autowired
	private ClienteResourceClient clienteResourceClient;
	
	@Autowired
	private CartoesResourceClient cartoesResourceClient;
	
	public SituacaoCliente obterSituacaoCliente(String cpf) {
		
		ResponseEntity<Client> dadosClienteResponse = clienteResourceClient.findByCpf(cpf);
		ResponseEntity<List<CartoesCliente>> cartoesResponse = cartoesResourceClient.getCartoesByRenda(cpf);
		
		return SituacaoCliente.builder()
				.cliente(dadosClienteResponse.getBody())
				.cartoes(cartoesResponse.getBody())
				.build();
	}

}
