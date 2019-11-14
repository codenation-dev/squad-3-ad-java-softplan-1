package br.com.squadjoaquina.errorlogger.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket apiDocket() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        "br.com.squadjoaquina.errorlogger"))
                .paths(PathSelectors.any())
                .build().apiInfo(metaData()).useDefaultResponseMessages(false).ignoredParameterTypes(
                        Pageable.class, Sort.class);

        return docket;
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo("ErrorLogger REST API",
                                      "API REST para registro  e " +
                                      "monitoramento de erros.",
                                      "1.0",
                                      "",
                                      "",
                                      "",
                                      "");
        return apiInfo;
    }

}