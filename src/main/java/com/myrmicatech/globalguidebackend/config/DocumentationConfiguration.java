package com.myrmicatech.globalguidebackend.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.http.HttpHeaders;
import java.util.*;

import static io.swagger.models.auth.In.HEADER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
public class DocumentationConfiguration {


	public static final Contact DEFAULT_CONTACT = new Contact("Shopizer", "https://www.shopizer.com", "");

	private static final String HOST = "localhost:8080";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.myrmicatech.globalguidebackend"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION, HEADER.name());
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

//	final Predicate<RequestHandler> requestHandlers() {
//
//		Set<Predicate<RequestHandler>> matchers = new HashSet<Predicate<RequestHandler>>();
//		matchers.add(RequestHandlerSelectors.basePackage("com.salesmanager.shop.store.api.v1"));
//		matchers.add(RequestHandlerSelectors.basePackage("com.salesmanager.shop.store.api.v2"));
//
//		return Predicates.or(matchers);
//
//	}
}
