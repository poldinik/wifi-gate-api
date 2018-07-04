package com.cosmink.services.security;


/**
 * Thrown if an authentication token is invalid.
 *
 * @author cassiomolin
 */
public class InvalidAuthenticationTokenException extends RuntimeException {

    public InvalidAuthenticationTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
