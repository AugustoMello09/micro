package io.gitHub.AugustoMello09.validadorCredito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException.FeignClientException;
import io.gitHub.AugustoMello09.validadorCredito.domain.CartoesCliente;
import io.gitHub.AugustoMello09.validadorCredito.domain.Client;
import io.gitHub.AugustoMello09.validadorCredito.domain.SituacaoCliente;
import io.gitHub.AugustoMello09.validadorCredito.execptions.DadosClienteNotFoundException;
import io.gitHub.AugustoMello09.validadorCredito.execptions.ErroComunicacaoMicrosserviceException;
import io.gitHub.AugustoMello09.validadorCredito.infra.clients.CartoesResourceClient;
import io.gitHub.AugustoMello09.validadorCredito.infra.clients.ClienteResourceClient;

@Service
public class AvaliadorCredidoService {
	
	@Autowired
	private ClienteResourceClient clienteResourceClient;
	
	@Autowired
	private CartoesResourceClient cartoesResourceClient;
	
	public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicrosserviceException {
		try {
			ResponseEntity<Client> dadosClienteResponse = clienteResourceClient.findByCpf(cpf);
			ResponseEntity<List<CartoesCliente>> cartoesResponse = cartoesResourceClient.getCartoesByRenda(cpf);
			
			return SituacaoCliente.builder()
					.cliente(dadosClienteResponse.getBody())
					.cartoes(cartoesResponse.getBody())
					.build();
		} catch (FeignClientException e) {
			int status = e.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new DadosClienteNotFoundException();
			}
			throw new ErroComunicacaoMicrosserviceException(e.getMessage(), status);
		}
		
	}

}
