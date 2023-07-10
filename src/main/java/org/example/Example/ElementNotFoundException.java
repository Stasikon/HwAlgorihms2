package org.example.Example;

public class ElementNotFoundException extends IllegalArgumentException{
    public ElementNotFoundException() {

    }
    public ElementNotFoundException(String s) {
        super(s);
    }

    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
