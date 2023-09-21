package io.github.AugustoMello09.mscartoes.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.AugustoMello09.mscartoes.domain.Cartao;
import io.github.AugustoMello09.mscartoes.dto.CartaoDTO;
import io.github.AugustoMello09.mscartoes.infra.repositories.CartaoRepository;

@Service
public class CartaoService {
	
	@Autowired
	private CartaoRepository repository;
	
	@Transactional
	public CartaoDTO save(CartaoDTO dto) {
		Cartao entity = new Cartao();
		entity.setNome(dto.getNome());
		entity.setBandeira(dto.getBandeira());
		entity.setRenda(dto.getRenda());
		entity.setLimiteBasico(dto.getLimite());
		repository.save(entity);
		return new CartaoDTO(entity);
	}
	
	public List<Cartao> getCartoesRendaMenorIgual(Long renda){
		var rendaBigdecimal = BigDecimal.valueOf(renda);
		return repository.findByRendaLessThanEqual(rendaBigdecimal);
	}
	
}
