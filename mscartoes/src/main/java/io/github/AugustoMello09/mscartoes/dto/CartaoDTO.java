package io.github.AugustoMello09.mscartoes.dto;

import java.math.BigDecimal;

import io.github.AugustoMello09.mscartoes.domain.BandeiraCartao;
import io.github.AugustoMello09.mscartoes.domain.Cartao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartaoDTO {
	
	private String nome;
	private BandeiraCartao bandeira;
	private BigDecimal renda;
	private BigDecimal limite;
	
	public CartaoDTO(Cartao entity) {
		nome = entity.getNome();
		bandeira = entity.getBandeira();
		renda = entity.getRenda();
		limite = entity.getLimiteBasico();
	}
}
