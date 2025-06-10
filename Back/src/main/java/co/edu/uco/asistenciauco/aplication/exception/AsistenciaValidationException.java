package co.edu.uco.asistenciauco.aplication.exception;

import java.util.List;
import java.util.ArrayList;

public class AsistenciaValidationException extends RuntimeException {
    private final List<String> errores;
    public AsistenciaValidationException(List<String> errores) {
        super(String.join(", ", errores));
        this.errores = errores;
    }
    public AsistenciaValidationException(String error) {
        super(error);
        this.errores = new ArrayList<>();
        this.errores.add(error);
    }
    public List<String> getErrores() {
        return errores;
    }
} 