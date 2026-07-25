package com.hugosantos.url_shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
		// Ver se ainda precisa configurar o Cassandra e o Redis no @Configuration ou se é feito automaticamente
		// Verificar se o redis consegue guardar o COUNTER_KEY em disco, ou se é perdido toda vez que a app é reiniciada
		// Implementar o hash do COUNTER_KEY gerado pra diminuir a previsibilidade do próximo hash gerado (ex: 14000000 -> 14000001 -> 14000002 -> 14000003 -> 14000004 -> 14000005 -> 14000006 -> 14000007 -> 14000008 -> 14000009 -> 14000010 -> 14000011 -> 14000012 -> 14000013 -> 14000014 -> 14000015 -> 14000016 -> 14000017 -> 14000018 -> 14000019 -> 14000020)
		// Criar .env e .env.example para guardar as variáveis de ambiente do Redis e Cassandra
		// Implementar Dockerfile e docker-compose.yml para rodar a aplicação com Cassandra e Redis
		// Criar README.md 
	}

}
