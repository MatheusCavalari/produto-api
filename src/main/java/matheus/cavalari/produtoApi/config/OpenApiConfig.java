package matheus.cavalari.produtoApi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Matheus",
                        email = "matheuscav@hotmail.com"
                ),
                description = "O Microserviço Produto Api, é responsável pela criação do Produto e listagem.",
                title = "Produto API",
                version = "1.0"
        )
)
public class OpenApiConfig {
}
