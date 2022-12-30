
package com.mirat.soap.config;

import com.mirat.soap.client.FilesHashClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.example.soap.file.wsdl");
		return marshaller;
	}

	@Bean
	public FilesHashClient filesClient(Jaxb2Marshaller marshaller) {
		FilesHashClient client = new FilesHashClient();
		client.setDefaultUri("http://localhost:8080/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
