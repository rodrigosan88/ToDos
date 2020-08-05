package com.rodrigosan88.todos.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigosan88.todos.services.WebClientSampleService;

@RestController
@RequestMapping("/third-party")
public class ThirdPartyRestController {
	
	@Autowired
	private WebClientSampleService webClientSampleService;
	
	@GetMapping(path = "/resources")
	public ResponseEntity<String> callThirdPartyServiceWithWebClientAndClientCredentials(){
		String response = this.webClientSampleService.callServiceUsingClientCredentials();
		
		return ResponseEntity.ok(response);
	}

}
