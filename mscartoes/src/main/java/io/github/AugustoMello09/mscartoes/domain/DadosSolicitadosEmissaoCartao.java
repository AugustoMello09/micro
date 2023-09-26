package io.github.AugustoMello09.mscartoes.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DadosSolicitadosEmissaoCartao {
	private Long idCartao;
	private String cpf;
	private String endereco;
	private BigDecimal limiteLiberado;
}
