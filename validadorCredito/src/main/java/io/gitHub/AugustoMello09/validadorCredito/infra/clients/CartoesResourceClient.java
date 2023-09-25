package io.gitHub.AugustoMello09.validadorCredito.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.gitHub.AugustoMello09.validadorCredito.domain.Cartao;
import io.gitHub.AugustoMello09.validadorCredito.domain.CartoesCliente;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesResourceClient {
	
	@GetMapping(params = "cpf")
	public ResponseEntity<List<CartoesCliente>> getCartoesByRenda(@RequestParam("cpf") String cpf);
	
	@GetMapping(params = "renda")
	public ResponseEntity<List<Cartao>> getCartaoesRendaAte(@RequestParam("renda") Long renda);

}
