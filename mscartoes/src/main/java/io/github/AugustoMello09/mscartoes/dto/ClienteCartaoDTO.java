package io.github.AugustoMello09.mscartoes.dto;

import java.math.BigDecimal;

import io.github.AugustoMello09.mscartoes.domain.BandeiraCartao;
import io.github.AugustoMello09.mscartoes.domain.ClienteCartao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteCartaoDTO {
	
	private String nome;
	private BandeiraCartao bandeira;
	private BigDecimal limeteLiberado;
	
	public ClienteCartaoDTO(ClienteCartao entity) {
		nome = entity.getCartao().getNome();
		bandeira = entity.getCartao().getBandeira();
		limeteLiberado = entity.getLimite();
	}
}
