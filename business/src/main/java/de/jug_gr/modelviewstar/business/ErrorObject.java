package de.jug_gr.modelviewstar.business;

public class ErrorObject {

    private final String message;

    private final String details;

    public static ErrorObject error(String message, String details){
        return new ErrorObject(message, details);
    }

    private ErrorObject(String message, String details){
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
