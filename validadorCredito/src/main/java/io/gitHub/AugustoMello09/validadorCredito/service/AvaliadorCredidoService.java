package io.gitHub.AugustoMello09.validadorCredito.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import feign.FeignException.FeignClientException;
import io.gitHub.AugustoMello09.validadorCredito.domain.Cartao;
import io.gitHub.AugustoMello09.validadorCredito.domain.CartoesAprovados;
import io.gitHub.AugustoMello09.validadorCredito.domain.CartoesCliente;
import io.gitHub.AugustoMello09.validadorCredito.domain.Client;
import io.gitHub.AugustoMello09.validadorCredito.domain.DadosSolicitadosEmissaoCartao;
import io.gitHub.AugustoMello09.validadorCredito.domain.ProtocoloSituacaoCartao;
import io.gitHub.AugustoMello09.validadorCredito.domain.RetornoAvaliacaoCliente;
import io.gitHub.AugustoMello09.validadorCredito.domain.SituacaoCliente;
import io.gitHub.AugustoMello09.validadorCredito.execptions.DadosClienteNotFoundException;
import io.gitHub.AugustoMello09.validadorCredito.execptions.ErroComunicacaoMicrosserviceException;
import io.gitHub.AugustoMello09.validadorCredito.execptions.ErroSolicitacao;
import io.gitHub.AugustoMello09.validadorCredito.infra.clients.CartoesResourceClient;
import io.gitHub.AugustoMello09.validadorCredito.infra.clients.ClienteResourceClient;
import io.gitHub.AugustoMello09.validadorCredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;

@Service
public class AvaliadorCredidoService {
	
	@Autowired
	private ClienteResourceClient clienteResourceClient;
	
	@Autowired
	private CartoesResourceClient cartoesResourceClient;
	
	@Autowired
	private SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;
	
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
	
	public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundException, ErroComunicacaoMicrosserviceException {
		try {
			ResponseEntity<Client> dadosClienteResponse = clienteResourceClient.findByCpf(cpf);
			ResponseEntity<List<Cartao>> cartoesResponse = cartoesResourceClient.getCartaoesRendaAte(renda);
			
			List<Cartao> cartoes = cartoesResponse.getBody();
			
			 List<CartoesAprovados> listaCartoesAprovados = cartoes.stream().map(cartao -> {
				CartoesAprovados aprovado = new CartoesAprovados();
				
				 Client dadosCliente = dadosClienteResponse.getBody();
				
				BigDecimal limiteBasico = cartao.getLimiteBasico();
				BigDecimal idadeBd = BigDecimal.valueOf(dadosCliente.getIdade());
				
				var fator = idadeBd.divide(BigDecimal.valueOf(10));
				BigDecimal limiteAprovado = fator.multiply(limiteBasico);
				
				aprovado.setNome(cartao.getNome());
				aprovado.setBandeira(cartao.getBandeira());
				aprovado.setLimiteAprovado(limiteAprovado);
				
				return aprovado;
				
			}).collect(Collectors.toList());
			
			return new RetornoAvaliacaoCliente(listaCartoesAprovados);
					
					
		} catch (FeignClientException e) {
			int status = e.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new DadosClienteNotFoundException();
			}
			throw new ErroComunicacaoMicrosserviceException(e.getMessage(), status);
		}
	}
	
	public ProtocoloSituacaoCartao solicitarEmissaoDeCartao(DadosSolicitadosEmissaoCartao dados) {
		try {
			emissaoCartaoPublisher.solicitarCartao(dados);
			var protoculo = UUID.randomUUID().toString();
			return new ProtocoloSituacaoCartao(protoculo);
		} catch (JsonProcessingException e) {
			throw new ErroSolicitacao(e.getMessage());
		}
	}

}
