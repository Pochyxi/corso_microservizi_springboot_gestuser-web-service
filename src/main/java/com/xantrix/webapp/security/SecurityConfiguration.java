package com.xantrix.webapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Configurazione di sicurezza per l'applicazione.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String[] ADMIN_MATCHER = { "/utenti/inserisci/**", "/utenti/elimina/**" };

	private final SecurityProperties securityProperties;

	public SecurityConfiguration(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	/**
	 * Configura le regole di sicurezza HTTP.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String REALM = "REAME";
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers(ADMIN_MATCHER).hasAnyRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	/**
	 * Definisce il punto di ingresso per l'autenticazione di base.
	 */
	@Bean
	public AuthEntryPoint getBasicAuthEntryPoint() {
		return new AuthEntryPoint();
	}

	/**
	 * Configura le regole di sicurezza Web, ignorando le richieste OPTIONS.
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	/**
	 * Definisce l'encoder per le password.
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configura il servizio di dettagli utente in memoria.
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		UserDetails readUser = User.builder()
				.username(securityProperties.getRead().getUsername())
				.password(new BCryptPasswordEncoder().encode(securityProperties.getRead().getPassword()))
				.roles("USER")
				.build();

		UserDetails writeUser = User.builder()
				.username(securityProperties.getWrite().getUsername())
				.password(new BCryptPasswordEncoder().encode(securityProperties.getWrite().getPassword()))
				.roles("USER", "ADMIN")
				.build();

		manager.createUser(readUser);
		manager.createUser(writeUser);

		return manager;
	}
}
