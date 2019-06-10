package com.fastchar.job.exceptions;

public class FastJobException extends RuntimeException {
    public FastJobException(String message) {
        super(message);
    }

    public FastJobException(Throwable cause) {
        super(cause);
    }
}
