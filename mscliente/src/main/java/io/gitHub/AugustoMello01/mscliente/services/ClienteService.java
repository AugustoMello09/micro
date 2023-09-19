package io.gitHub.AugustoMello01.mscliente.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.gitHub.AugustoMello01.mscliente.domain.Client;
import io.gitHub.AugustoMello01.mscliente.dto.ClientDTO;
import io.gitHub.AugustoMello01.mscliente.infre.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Transactional
	public ClientDTO save(ClientDTO dto) {
		Client entity = new Client();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf());
		entity.setIdade(dto.getIdade());
		repository.save(entity);
		return new ClientDTO(entity);
	}
	
	public Optional<Client> findByCpf(String cpf){
		return repository.findByCpf(cpf);
	}
	
}
