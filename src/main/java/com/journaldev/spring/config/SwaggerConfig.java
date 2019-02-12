package com.journaldev.spring.config;

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
	    public Docket swaggerRentalApi10() 
	    {
	        return new Docket(DocumentationType.SWAGGER_2)
	        		   .groupName("rental-api-1.0")
	                   .select()
	                   .apis(RequestHandlerSelectors
							   .basePackage("com.journaldev.spring.controllers"))
	                           .paths(PathSelectors.regex("/rental/.*/v1.0"))
	                           .build()
	                           .pathMapping("/")
	                           .apiInfo(new ApiInfoBuilder()
									   .version("1.0").title("Rental Home API")
							           .description("Documentation RentalHome API v1.0")
									   .build());
	    }
	    
	  @Bean 
	  public Docket swaggerRentalApi11() 
	   { 
		  return new Docket(DocumentationType.SWAGGER_2) 
				    .groupName("rental-api-1.1") 
				    .select()
	                .apis(RequestHandlerSelectors
	                .basePackage("com.journaldev.spring"))
	                .paths(PathSelectors.regex("/rental/.*/v1.1"))            
	                .build()
	                .pathMapping("/") 
	                .apiInfo(new
	                 ApiInfoBuilder()
	                .version("1.1")
	                .title("Rental Home API").
	                 description("Documentation RentalHome API v1.1")
	                .build()); }
	  
	  
	  @Bean public Docket swaggerRentalApi12() 
	  { 
		  return new Docket(DocumentationType.SWAGGER_2) 
				    .groupName("rental-api-1.2") 
				    .select()
	                .apis(RequestHandlerSelectors
	                .basePackage("com.journaldev.spring"))
	                .paths(PathSelectors.regex("/rental/.*/v1.2"))                     
	                .build()
	                .pathMapping("/")
	                .apiInfo(new
	                 ApiInfoBuilder()
	                .version("1.0")
	                .title("Rental Home API").
	                 description("Documentation RentalHome API v1.2")
	                .build()); 
		  }
	  

		private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	            .title("Person")
	            .description("Person API author Sanket")
	            .version("VERSION")
	            .termsOfServiceUrl("http://terms-of-services.url")
	            .license("LICENSE")
	            .licenseUrl("http://url-to-license.com")
	            .build();
	    }
}
