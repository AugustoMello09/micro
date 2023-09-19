package io.gitHub.AugustoMello01.mscliente.dto;

import io.gitHub.AugustoMello01.mscliente.domain.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor
	public class ClientDTO {
		
		private Long id;
		private String cpf;
		private String nome;
		private Integer idade;
		
		public ClientDTO(Client entity) {
			id = entity.getId();
			nome = entity.getNome();
			cpf = entity.getCpf();
			idade = entity.getIdade();
		}
		
	}
