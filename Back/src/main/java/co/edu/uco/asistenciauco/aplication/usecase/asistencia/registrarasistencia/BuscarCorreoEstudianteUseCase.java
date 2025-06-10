package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia;

import java.util.UUID;

public interface BuscarCorreoEstudianteUseCase {
    String ejecutar(UUID estudiante);
}