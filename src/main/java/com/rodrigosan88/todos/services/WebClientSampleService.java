package com.rodrigosan88.todos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientSampleService {

	@Autowired
	private WebClient webClient;
	
	public String callServiceUsingClientCredentials() {
		String response = this.webClient
				.get()
				.uri("http://localhost:8880/api/v1/hello/qwe")
				.attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId("todos"))
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		return response;
	}
	
}
