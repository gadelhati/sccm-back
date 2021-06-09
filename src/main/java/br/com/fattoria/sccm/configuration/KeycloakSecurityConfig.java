package br.com.fattoria.sccm.configuration;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
	
	@Value("${keycloak.auth-server-url}")
	public static String AUTH_SERVER;

	@Value("${keycloak.credentials.secret}")
	public static String CLIENT_SECRET;

	@Value("${keycloak.resource}")
	public static String CLIENT_ID;

	@Value("${keycloak.realm}")
	public static String REALM;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
            .antMatchers("/api/*").hasAnyRole("user","admin");
            //.anyRequest()
            //.permitAll();
        http.csrf().disable();
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
		keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
		auth.authenticationProvider(keycloakAuthenticationProvider);
	}

	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
	}
	
//    @Bean
//    @Primary
//    public KeycloakConfigResolver keycloakConfigResolver(KeycloakSpringBootProperties properties) {
//        return new CustomKeycloakSpringBootConfigResolver(properties);
//    }

	@Bean
	public KeycloakConfigResolver KeycloakConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}
	
}
