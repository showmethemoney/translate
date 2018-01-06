package com.fubon.esb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@ComponentScan(basePackages = "com.fubon.esb")
public class AppConfig {

	private static final String NAMED_REFLECTION_MESSAGE_PACKAGE = "com.fubon.esb.bean";
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setPackagesToScan( NAMED_REFLECTION_MESSAGE_PACKAGE );

		return jaxb2Marshaller;
	}
}
