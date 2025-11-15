package hr.lknezevic.exceptions;

import lombok.Getter;

@Getter
public class N8nClientException extends RuntimeException {
    private final Integer statusCode;

    public N8nClientException(String message) {
        super(message);
        this.statusCode = null;
    }

    public N8nClientException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public N8nClientException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = null;
    }

    public N8nClientException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }
}
