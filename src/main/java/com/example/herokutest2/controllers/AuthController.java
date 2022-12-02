package com.example.testheroku.controllers;


import com.example.testheroku.Security.JwtUtils;
import com.example.testheroku.Security.LoginRequest;
import com.example.testheroku.Security.LoginResponse;
import com.example.testheroku.Security.UserDetailsImpl;
import com.example.testheroku.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService us;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
		);

		
		authentication.getAuthorities();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = ( UserDetailsImpl ) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map( GrantedAuthority::getAuthority )
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new LoginResponse(jwt, Math.toIntExact( userDetails.getId() ), userDetails.getUsername(),
						userDetails.getEmail(), roles, userDetails.getExpirationTime()));
	}

}
