package br.com.boilerplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.boilerplate"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(appInfo());
    }

    private ApiInfo appInfo() {
        return new ApiInfoBuilder()
                .title("Name API")
                .description("Documentação de todos os serviços disponíveis para comunicação com a API do aplicativo.")
                .version("1.0.0")
                .build();
    }

}
