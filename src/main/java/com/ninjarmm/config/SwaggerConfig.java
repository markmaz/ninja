package com.ninjarmm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.util.ReferenceSerializationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.paths.DefaultPathProvider;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig
{
    @Autowired
    ServletContext context;

    @Bean
    public SwaggerJacksonModuleRegistrar swaggerJacksonModuleRegistrar() {
        return new SwaggerJacksonModuleRegistrar();
    }

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicate.not(PathSelectors.regex("/error.*")))
                .build()
                .pathProvider(new PathProvider(context))
                .apiInfo(getApiInfo())
                .securitySchemes(securitySchemes());
    }

    private ApiInfo getApiInfo()
    {
        return new ApiInfo(
                "Mark's Swagger Documentation for Code Assignment",
                "Mark's Swagger Documentation for Code Assignment",
                "1.0",
                "http://www.maslakowski.com",
                new Contact(
                        "Mark Maslakowski",
                        "http://www.maslakowski.com",
                        "mark@maslakowski.com"),
                "Internal Use Only",
                "https://www.maslakowski.com",
                Collections.emptyList());
    }

    private static List<SecurityScheme> securitySchemes()
    {
        return List.of(new ApiKey("Bearer", "Authorization", "header"));
    }

    private static class PathProvider extends DefaultPathProvider
    {
        private String contextPath = null;

        public PathProvider(ServletContext context)
        {
            contextPath = context.getContextPath();
        }

        @Override
        public String getOperationPath(String operationPath)
        {
            if (contextPath != null && operationPath.startsWith(contextPath))
            {
                operationPath = operationPath.substring(contextPath.length());
            }
            return Paths.removeAdjacentForwardSlashes(UriComponentsBuilder.newInstance().replacePath(operationPath).build().toString());
        }
    }
}

class SwaggerJacksonModuleRegistrar implements JacksonModuleRegistrar
{
    @Override
    public void maybeRegisterModule(ObjectMapper objectMapper) {
        ReferenceSerializationConfigurer.serializeAsComputedRef(objectMapper);
    }
}

