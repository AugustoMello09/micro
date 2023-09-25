package io.gitHub.AugustoMello09.validadorCredito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.gitHub.AugustoMello09.validadorCredito.domain.DadosAvaliacao;
import io.gitHub.AugustoMello09.validadorCredito.domain.RetornoAvaliacaoCliente;
import io.gitHub.AugustoMello09.validadorCredito.domain.SituacaoCliente;
import io.gitHub.AugustoMello09.validadorCredito.execptions.DadosClienteNotFoundException;
import io.gitHub.AugustoMello09.validadorCredito.execptions.ErroComunicacaoMicrosserviceException;
import io.gitHub.AugustoMello09.validadorCredito.service.AvaliadorCredidoService;

@RestController
@RequestMapping(value = "/avaliacoes-credito")
public class AvaliadorCreditoController {

	@Autowired
	private AvaliadorCredidoService avaliadorCredidoService;

	@GetMapping
	public String status() {
		return "ok";
	}

	@GetMapping(value = "/situacao-cliente", params = "cpf")
	public ResponseEntity<SituacaoCliente> consultaSituacaoCliente(@RequestParam("cpf") String cpf) {

		try {
			SituacaoCliente situacaoCliente = avaliadorCredidoService.obterSituacaoCliente(cpf);
			return ResponseEntity.ok().body(situacaoCliente);
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (ErroComunicacaoMicrosserviceException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).build();
		}

	}
	
	@PostMapping
	public ResponseEntity<RetornoAvaliacaoCliente> realizarAvaliacao(@RequestBody DadosAvaliacao dados){
		try {
			RetornoAvaliacaoCliente retornoAvaliacaoCliente	= avaliadorCredidoService.realizarAvaliacao(dados.getCpf(), dados.getRenda());
			return ResponseEntity.ok().body(retornoAvaliacaoCliente);
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (ErroComunicacaoMicrosserviceException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).build();
		}
	}
	
}
