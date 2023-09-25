package io.gitHub.AugustoMello09.validadorCredito.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartoesAprovados {
	private String nome;
	private String cartao;
	private String bandeira;
	private BigDecimal limiteAprovado;
}
