package org.sing.tcode.exception;

/**
 * @author Chunkie Yeung
 *
 */
public class UserException extends RuntimeException{

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(String message) {
		super(message);
	}

	public UserException(Throwable cause) {
		super(cause);
	}

	
}
