package rest.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan("rest.client")
public class SpringConfig {
	@Bean
	public RestTemplate restTemplateBean() {
		return new RestTemplate();
	}
	@Bean 
	public ObjectMapper objectMapperBean() {
		return new ObjectMapper();
	}
}
