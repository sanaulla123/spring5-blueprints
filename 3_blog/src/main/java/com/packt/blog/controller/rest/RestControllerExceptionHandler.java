package com.packt.blog.controller.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler(HttpServletRequest request, Exception e) {
		Map<String, Object> response = new HashMap<>();
		response.put("message", e.getMessage());
		response.put("cause", e.getCause() != null? e.getCause().getMessage() : "");
		response.put("request_url", request.getRequestURI());
		response.put("params", request.getParameterMap());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		
	}
}
