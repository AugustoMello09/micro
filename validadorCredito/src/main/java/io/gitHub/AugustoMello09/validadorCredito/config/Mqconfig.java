package io.gitHub.AugustoMello09.validadorCredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mqconfig {
	
	@Value("${mq.queues.emissao-cartoes}")
	private String emissaoCartoesFila;
	
	@Bean
	public Queue queueEmissoaCartao() {
		return new Queue(emissaoCartoesFila, true);
	}
}
