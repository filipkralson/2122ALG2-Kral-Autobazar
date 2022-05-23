package com.filipkral.autobazar.utils;

/**
 * Class with custom ExceptionInputOutput
 * 
 * @author filip.kral
 */
public class ExceptionInputOutput extends IllegalArgumentException {

    public ExceptionInputOutput(String message) {
        super(message);
    }
    
}
