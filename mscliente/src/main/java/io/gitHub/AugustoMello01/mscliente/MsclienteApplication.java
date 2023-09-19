package io.gitHub.AugustoMello01.mscliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsclienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsclienteApplication.class, args);
	}

}
