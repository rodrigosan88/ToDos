package com.rodrigosan88.todos.security;

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.rodrigosan88.todos.models.entities.User;
import com.rodrigosan88.todos.repositories.UserRepository;

public class TodosAuthenticationProvider extends KeycloakAuthenticationProvider{

	private UserRepository userRepository;
	
	public TodosAuthenticationProvider(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		KeycloakAuthenticationToken auth = (KeycloakAuthenticationToken)super.authenticate(authentication);
		
		processLocalUser((SimpleKeycloakAccount) auth.getDetails());
		
		return super.authenticate(authentication);
	}

	private void processLocalUser(SimpleKeycloakAccount account) {
		
		User user = this.userRepository.findByUsername(account.getPrincipal().getName());
		
		if(user == null) {
			user = new User();
			user.setActive(true);
			user.setEmail(account.getKeycloakSecurityContext().getToken().getEmail());
			user.setUsername(account.getPrincipal().getName());
			this.getUserRepository().save(user);			
		}
		
	}
	
	public UserRepository getUserRepository() {
		return userRepository;
	}
	
}
