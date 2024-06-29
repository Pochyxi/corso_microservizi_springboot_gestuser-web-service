package com.xantrix.webapp;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


/**
 * Configurazione per la gestione dei messaggi e della localizzazione.
 */
@Configuration
public class MessageConfig {

	/**
	 * Configura il validatore per utilizzare il MessageSource per i messaggi di validazione.
	 *
	 * @return LocalValidatorFactoryBean configurato
	 */
	@Bean(name = "validator")
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	/**
	 * Configura il resolver per la localizzazione.
	 * Imposta l'italiano come locale predefinito.
	 *
	 * @return LocaleResolver configurato
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(new Locale("it"));
		return sessionLocaleResolver;
	}

	/**
	 * Configura la sorgente dei messaggi.
	 * Utilizza un file di risorse chiamato "messages" per i messaggi localizzati.
	 *
	 * @return MessageSource configurato
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		resource.setUseCodeAsDefaultMessage(true);
		return resource;
	}
}
