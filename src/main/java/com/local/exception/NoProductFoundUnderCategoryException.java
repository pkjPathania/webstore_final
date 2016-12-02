package com.local.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Selected Product Not Found")
public class NoProductFoundUnderCategoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
