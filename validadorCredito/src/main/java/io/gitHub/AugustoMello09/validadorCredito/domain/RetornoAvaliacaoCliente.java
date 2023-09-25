package io.gitHub.AugustoMello09.validadorCredito.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente {
 private List<CartoesAprovados> cartoes;
}
