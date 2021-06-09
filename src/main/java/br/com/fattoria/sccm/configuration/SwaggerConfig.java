package br.com.fattoria.sccm.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.Response;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({ springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class })
public class SwaggerConfig extends WebMvcConfigurationSupport {
	
	@Value("${keycloak.auth-server-url}")
	private String AUTH_SERVER;

	@Value("${keycloak.credentials.secret}")
	private String CLIENT_SECRET;

	@Value("${keycloak.resource}")
	private String CLIENT_ID;

	@Value("${keycloak.realm}")
	private String REALM;

	private static final String OAUTH_NAME = "spring_oauth";
	private static final String ALLOWED_PATHS = "/api/*";
	private static final String GROUP_NAME = "controle-fitoteca";
	private static final String TITLE = "Spring Boot REST API for fitoteca - Marinha";
	private static final String DESCRIPTION = "Spring Boot REST API";
	private static final String VERSION = "1.0";

	private List<Response> responseMessageForGET() {
		List<Response> arrayList = new ArrayList<Response>();

		arrayList.add(new ResponseBuilder().code("200").description("OK").build());
		arrayList.add(new ResponseBuilder().code("401").description("Unauthorized!").build());
		arrayList.add(new ResponseBuilder().code("403").description("Forbidden!").build());
		arrayList.add(new ResponseBuilder().code("404").description("Not Found!").build());
		arrayList.add(new ResponseBuilder().code("500").description("500 message").build());

		return arrayList;
	}

	private final String baseUrl;

	public SwaggerConfig(@Value("${springfox.documentation.swagger-ui.base-url:}") String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
		registry.addResourceHandler(baseUrl + "/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
				.resourceChain(false);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController(baseUrl + "/swagger-ui/")
				.setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(TITLE).description(DESCRIPTION).version(VERSION).build();
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2).groupName(GROUP_NAME)
//				.useDefaultResponseMessages(true)
				.useDefaultResponseMessages(false)
		        .globalResponses(HttpMethod.GET, responseMessageForGET())
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
//				.paths(regex(ALLOWED_PATHS))
				.build()
				.securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext())
				);
	}

	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder().realm(REALM).clientId(CLIENT_ID).clientSecret(CLIENT_SECRET)
				.appName(GROUP_NAME).scopeSeparator(" ").build();
	}

	private SecurityScheme securityScheme() {
		
		TokenEndpoint tokenEndpoint = new TokenEndpoint(AUTH_SERVER + "/realms/" + REALM + "/protocol/openid-connect/token",
				GROUP_NAME);
		
		TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint(
				AUTH_SERVER + "/realms/" + REALM + "/protocol/openid-connect/auth", CLIENT_ID, CLIENT_SECRET);
		
		GrantType grantType = new AuthorizationCodeGrantBuilder()
				.tokenEndpoint(tokenEndpoint)
				.tokenRequestEndpoint(tokenRequestEndpoint)
				.build();

		SecurityScheme oauth = new OAuthBuilder().name(OAUTH_NAME).grantTypes(Arrays.asList(grantType))
				.scopes(Arrays.asList(scopes())).build();
		return oauth;
	}

	private AuthorizationScope[] scopes() {
		AuthorizationScope[] scopes = { new AuthorizationScope("user", "for users operations"),
				new AuthorizationScope("admin", "for admin operations")
				};
		return scopes;
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(Arrays.asList(new SecurityReference(OAUTH_NAME, scopes())))
//				.forPaths(regex(ALLOWED_PATHS))
				.build();
	}

}
