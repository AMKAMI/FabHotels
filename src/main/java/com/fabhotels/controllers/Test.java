package com.fabhotels.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

	@GetMapping("/test")
	public String getResponse()
	{
		return "Test Success";
	}
}
