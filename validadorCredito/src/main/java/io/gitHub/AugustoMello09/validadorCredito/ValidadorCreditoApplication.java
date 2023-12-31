package io.gitHub.AugustoMello09.validadorCredito;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableRabbit
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class ValidadorCreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidadorCreditoApplication.class, args);
	}

}
