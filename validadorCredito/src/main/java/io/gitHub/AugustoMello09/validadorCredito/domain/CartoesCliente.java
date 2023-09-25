package io.gitHub.AugustoMello09.validadorCredito.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartoesCliente {
	
	private String nome;
	private String bandeira;
	private BigDecimal limiteLiberado;
}
