package tech.ada.fintechADAConta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FintechAdaContaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FintechAdaContaApplication.class, args);
	}

}
