package com.brihaspathee.zeus.exception;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, July 2022
 * Time: 1:57 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.exception
 * To change this template use File | Settings | File and Code Template
 */
public class AuthorityNotFoundException extends RuntimeException{

    /**
     * Constructor with the message
     * @param message
     */
    public AuthorityNotFoundException(String message){
        super(message);
    }

    /**
     * Constructor with the message and cause
     * @param message
     * @param cause
     */
    public AuthorityNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
