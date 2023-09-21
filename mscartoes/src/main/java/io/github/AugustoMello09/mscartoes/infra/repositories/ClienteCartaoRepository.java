package io.github.AugustoMello09.mscartoes.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.AugustoMello09.mscartoes.domain.ClienteCartao;

@Repository
public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {

	List<ClienteCartao> findByCpf(String cpf);
}
