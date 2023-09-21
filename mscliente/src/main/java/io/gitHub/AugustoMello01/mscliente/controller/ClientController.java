package io.gitHub.AugustoMello01.mscliente.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.gitHub.AugustoMello01.mscliente.domain.Client;
import io.gitHub.AugustoMello01.mscliente.dto.ClientDTO;
import io.gitHub.AugustoMello01.mscliente.services.ClienteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/clientes")
public class ClientController {
	
	@Autowired
	private ClienteService service;
	
	
	@GetMapping
	public String status () {
		log.info("Obtendo o status do microsservice de cliente");
		return "ok";
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO dto){
		ClientDTO objDto = service.save(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.query("cpf={cpf}")
				.buildAndExpand(objDto.getCpf()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(params = "cpf")
	public ResponseEntity<Optional<Client>> findByCpf(@RequestParam("cpf") String cpf){
		Optional<Client> client = service.findByCpf(cpf);
		if (client.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(client);
	}
}
