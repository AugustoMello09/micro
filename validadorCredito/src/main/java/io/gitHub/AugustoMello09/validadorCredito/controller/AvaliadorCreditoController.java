package io.gitHub.AugustoMello09.validadorCredito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.gitHub.AugustoMello09.validadorCredito.domain.SituacaoCliente;
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
	public ResponseEntity<SituacaoCliente> consultaSituacaoCliente(@RequestParam("cpf")String cpf){
		SituacaoCliente situacaoCliente = avaliadorCredidoService.obterSituacaoCliente(cpf);
		return ResponseEntity.ok().body(situacaoCliente);
	}
}
