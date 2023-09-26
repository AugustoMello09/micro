package io.github.AugustoMello09.mscartoes;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableRabbit
@EnableEurekaClient
@SpringBootApplication
public class MscartoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscartoesApplication.class, args);
	}

}
