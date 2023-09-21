package io.github.AugustoMello09.mscartoes.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.AugustoMello09.mscartoes.domain.Cartao;
import io.github.AugustoMello09.mscartoes.domain.ClienteCartao;
import io.github.AugustoMello09.mscartoes.dto.CartaoDTO;
import io.github.AugustoMello09.mscartoes.dto.ClienteCartaoDTO;
import io.github.AugustoMello09.mscartoes.service.CartaoService;
import io.github.AugustoMello09.mscartoes.service.ClienteCartaoService;

@RestController
@RequestMapping(value = "/cartoes")
public class CartoesController {
	
	@Autowired
	private CartaoService service;
	
    @Autowired
	private ClienteCartaoService clienteCartaoService;
	
	@GetMapping
	public String status() {
		return "ok";
	}
	
	@PostMapping
	public ResponseEntity<CartaoDTO> save(@RequestBody CartaoDTO obj) {
		service.save(obj);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(params = "renda")
	public ResponseEntity<List<Cartao>> getCartaoesRendaAte(@RequestParam("renda") Long renda){
		List<Cartao> lista = service.getCartoesRendaMenorIgual(renda);
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(params = "cpf")
	public ResponseEntity<List<ClienteCartaoDTO>> getCartoesByRenda(@RequestParam("cpf") String cpf){
		List<ClienteCartao> list = clienteCartaoService.listarCartaoByCpf(cpf);
		List<ClienteCartaoDTO> listDto = list.stream().map(ClienteCartaoDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

}
