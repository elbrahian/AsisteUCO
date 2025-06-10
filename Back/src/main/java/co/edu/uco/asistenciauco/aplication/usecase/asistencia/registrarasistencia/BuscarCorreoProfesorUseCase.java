package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia;

import java.util.UUID;

public interface BuscarCorreoProfesorUseCase {
    String ejecutar(UUID profesorId);
} 