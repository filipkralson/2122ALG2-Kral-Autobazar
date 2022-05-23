package com.filipkral.autobazar.utils;

import java.io.FileNotFoundException;

/**
 * Class with custom ExceptionFileNotFound
 * 
 * @author filip.kral
 */
public class ExceptionFileNotFound extends FileNotFoundException {

    public ExceptionFileNotFound(String message) {
        super(message);
    }
    
}
