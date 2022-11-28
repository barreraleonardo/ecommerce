package com.nocountry.ecommerce.configuration;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

   @Bean
   public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
         .apiInfo(apiInfo())
         .securityContexts(Lists.newArrayList(securityConfig()))
         .securitySchemes(Lists.newArrayList(apiKey()))
         .select()
         .apis(RequestHandlerSelectors.basePackage("com.nocountry.ecommerce.ports.input.rs.controller"))
         .paths(PathSelectors.any())
         .build();
   }

   private ApiKey apiKey() {
      return new ApiKey("JWT", "Authorization", "header");
   }

   private SecurityContext securityConfig() {
      return SecurityContext.builder().securityReferences(defaultAuthentication()).build();
   }

   private List<SecurityReference> defaultAuthentication() {
      AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
      AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
      authorizationScopes[0] = authorizationScope;
      return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
   }

   private ApiInfo apiInfo() {
      return new ApiInfo(
         "Ecommerce Api REST",
         "Website to buy gaming products." +
            "\nProject members: Cristian Aguirre, Joel Rodriguez, Leonardo Barrera, Federico Castro, Sofia Ríos Martinez.",
         "1.0",
         "No Country project",
         new Contact("", "", ""),
         "License of API", "API license URL", Collections.emptyList());
   }
}
