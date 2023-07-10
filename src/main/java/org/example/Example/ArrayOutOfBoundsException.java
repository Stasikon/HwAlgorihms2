package org.example.Example;

public class ArrayOutOfBoundsException extends IndexOutOfBoundsException {
    public ArrayOutOfBoundsException() {

    }
    public ArrayOutOfBoundsException(String s) {
        super(s);
    }

    public ArrayOutOfBoundsException(int index) {
        super(index);
    }
}
