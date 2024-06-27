package com.xantrix.webapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application-secret.yml")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{

    //private static final String[] USER_MATCHER = { "/utenti/cerca/**"};
	private static final String[] ADMIN_MATCHER = { "/utenti/inserisci/**", "/utenti/elimina/**" };

	private final SecurityProperties securityProperties;

	public SecurityConfiguration(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
        String REALM = "REAME";
        http.csrf().disable()
				.authorizeRequests()
				//.antMatchers(USER_MATCHER).hasAnyRole("USER")
				.antMatchers(ADMIN_MATCHER).hasAnyRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.httpBasic().realmName( REALM ).authenticationEntryPoint(getBasicAuthEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public AuthEntryPoint getBasicAuthEntryPoint()
	{
		return new AuthEntryPoint();
	}

	/* To allow Pre-flight [OPTIONS] request from browser */
	@Override
	public void configure(WebSecurity web)
	{
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

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
