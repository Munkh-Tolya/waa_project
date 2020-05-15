package edu.miu.cs545.waa_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Please upload valid image")
public class InvalidImageUploadException extends RuntimeException {

	public String getFullMessage(){
		return " Only image files are allowed to be uploaded.";
	}
}
