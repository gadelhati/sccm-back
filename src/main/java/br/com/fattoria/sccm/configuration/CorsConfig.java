package br.com.fattoria.sccm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
    	return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
               
                registry.addMapping("/**")
                .allowedOrigins("/**")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
            }
        };
    }
}