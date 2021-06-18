package ru.wt5n.microservice.configs;

import java.util.Arrays;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("bearer-jwt",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
								.in(SecurityScheme.In.HEADER).name("Authorization")))
				.info(new Info().title("Simple shop")
						.contact(new Contact().name("Владимир Захаров").email("qe.wtsn@gmail.com")))
				.addSecurityItem(
						new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
	}
}

