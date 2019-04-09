package com.proyecto.springboot.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocaleControle {
	
	@GetMapping("/locale")
	public String locale(HttpServletRequest request) {
		String ultimaUrl = request.getHeader("referer");
		
		return "redirect:".concat(ultimaUrl);
	}
}
