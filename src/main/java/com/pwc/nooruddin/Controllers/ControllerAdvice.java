package com.pwc.nooruddin.Controllers;

import java.nio.file.FileAlreadyExistsException;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pwc.nooruddin.CustomExceptions.productNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {
		
	@ExceptionHandler(value = FileAlreadyExistsException.class)
		public ResponseEntity<?> FileAlreadyExistException(FileAlreadyExistsException exception){
			return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
		}

	@ExceptionHandler(value = productNotFoundException.class)
	public ResponseEntity<?> productNotFoundException(productNotFoundException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
	
	@ExceptionHandler(value = NoSuchFieldException.class)
	public ResponseEntity<?> NoSuchFieldException(NoSuchMethodException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getStackTrace());
	}
	@ExceptionHandler(value = IncorrectResultSizeDataAccessException.class)
	public ResponseEntity<?> IncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
}
