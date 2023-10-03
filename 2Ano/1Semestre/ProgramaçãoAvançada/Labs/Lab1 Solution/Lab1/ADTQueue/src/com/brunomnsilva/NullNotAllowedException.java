package com.brunomnsilva;

public class NullNotAllowedException extends RuntimeException {
    public NullNotAllowedException() {
        super("Null is not allowed.");
    }
}
