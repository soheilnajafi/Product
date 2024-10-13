package com.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

	private final long serialVersionUid = 1L;

	public ProductNotFoundException(String message) {
		super(message);
	}
}
