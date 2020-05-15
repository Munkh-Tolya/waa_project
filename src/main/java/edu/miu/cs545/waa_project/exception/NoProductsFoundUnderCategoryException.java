package edu.miu.cs545.waa_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No products found under this category.")
public class NoProductsFoundUnderCategoryException extends RuntimeException {

	private String message;

	public NoProductsFoundUnderCategoryException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
