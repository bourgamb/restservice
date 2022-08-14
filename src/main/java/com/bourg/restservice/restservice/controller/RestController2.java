package com.bourg.restservice.restservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bourg.restservice.restservice.domain.Foo;

@RestController
@RequestMapping(value = "/v1")
public class RestController2 {

	@PreAuthorize("hasPermission(#foo, 'FOO_READ_PRIVILEGE')")
	@GetMapping("/hello")
	public String myTest(@RequestBody Foo foo) {
		
		return "Hello there";
	
	}
	
}