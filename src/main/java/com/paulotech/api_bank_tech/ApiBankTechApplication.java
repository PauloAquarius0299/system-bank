package com.paulotech.api_bank_tech;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import jakarta.validation.valueextraction.ExtractedValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API de banco",
				version = "1.0",
				description = "API de operações bancárias com funcionalidades de criação de conta, consulta de saldo," +
						" transferência e envio de e-mail.",
                contact = @Contact(
						name = "Paulo Tech",
						email = "paulocgsantos99@gmail.com",
						url = "https://paulotech.vercel.app/"
                ),
				license = @License(
						name = "Paulo Tech",
						url = "https://paulotech.vercel.app/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Documentação externa",
				url = "https://github.com/PauloAquarius0299/system-bank"
		)
)
public class ApiBankTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBankTechApplication.class, args);
	}

}
