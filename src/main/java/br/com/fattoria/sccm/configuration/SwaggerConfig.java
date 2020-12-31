package br.com.fattoria.sccm.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

//	private List<Response> responseMessageForGET() {
//		return new ArrayList<Response>() {
//			{
//				add(new ResponseBuilder().code("500").description("500 message").build());
//				add(new ResponseBuilder().code("403").description("Forbidden!").build());
//			}
//		};
//	}

	private final String baseUrl;

	  public SwaggerConfig(
	      @Value("${springfox.documentation.swagger-ui.base-url:}") String baseUrl) {
	    this.baseUrl = baseUrl;
	  }
	
	 @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
	    registry.
	        addResourceHandler(baseUrl + "/swagger-ui/**")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
	        .resourceChain(false);
	  }

	  @Override
	  public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController(baseUrl + "/swagger-ui/")
	        .setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
	  }

//	  @Override
//	  public void addCorsMappings(CorsRegistry registry) {
//	    registry
//	        .addMapping("/api/pet")
//	        .allowedOrigins("http://editor.swagger.io");
//	    registry
//	        .addMapping("/v2/api-docs.*")
//	        .allowedOrigins("http://editor.swagger.io");
//	  }

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring Boot REST API for fitoteca - Marinha").description(
				"Spring Boot REST API")
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.termsOfServiceUrl("").version("1.0.0")
				.contact(new Contact("Everton", "www.fattoria.com.br", "everton@fattoriaweb.com.br")).build();
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.fattoria.sccm.controller")).build()
				.apiInfo(apiInfo());
	}

}
