package com.mcommandes.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CommandeAjoutImpossibleException extends RuntimeException {

	public CommandeAjoutImpossibleException(String message) {

		super(message);
	}
}
