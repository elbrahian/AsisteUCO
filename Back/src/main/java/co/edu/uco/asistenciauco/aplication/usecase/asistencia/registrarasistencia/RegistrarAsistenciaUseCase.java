package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia;

import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain.AsistenciaDomain;

public interface RegistrarAsistenciaUseCase {
    void ejecutar(AsistenciaDomain domain);
}