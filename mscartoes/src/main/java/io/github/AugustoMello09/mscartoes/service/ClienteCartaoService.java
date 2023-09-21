package io.github.AugustoMello09.mscartoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.AugustoMello09.mscartoes.domain.ClienteCartao;
import io.github.AugustoMello09.mscartoes.infra.repositories.ClienteCartaoRepository;

@Service	
public class ClienteCartaoService {
	
	@Autowired
	private ClienteCartaoRepository repository;
	
	public List<ClienteCartao> listarCartaoByCpf(String cpf){
		return repository.findByCpf(cpf);	
	}
}
