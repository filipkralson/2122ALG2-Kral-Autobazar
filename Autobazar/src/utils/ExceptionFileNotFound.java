package utils;

import java.io.FileNotFoundException;

public class ExceptionFileNotFound extends FileNotFoundException {

    public ExceptionFileNotFound(String message) {
        super(message);
    }
    
}
