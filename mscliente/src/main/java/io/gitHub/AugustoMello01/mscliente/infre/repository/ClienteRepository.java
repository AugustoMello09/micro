package io.gitHub.AugustoMello01.mscliente.infre.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.gitHub.AugustoMello01.mscliente.domain.Client;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Long> {

	Optional<Client> findByCpf(String cpf);

}
