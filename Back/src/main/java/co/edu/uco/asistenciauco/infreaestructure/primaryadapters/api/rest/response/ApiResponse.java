package co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.response;

import java.util.List;

public class ApiResponse<T> {
    private final boolean success;
    private final String message;
    private final T data;
    private final List<String> errors;

    public ApiResponse(boolean success, String message, T data) {
        this(success, message, data, null);
    }
    public ApiResponse(boolean success, String message, T data, List<String> errors) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
    public T getData() {
        return data;
    }
    public List<String> getErrors() {
        return errors;
    }
} 