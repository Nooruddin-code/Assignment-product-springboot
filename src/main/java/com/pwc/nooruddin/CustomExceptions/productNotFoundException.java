package com.pwc.nooruddin.CustomExceptions;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class productNotFoundException extends Exception {
	public productNotFoundException(String ExceptionName) {
		super(ExceptionName);
	}
}
