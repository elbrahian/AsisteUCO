package co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia;

import co.edu.uco.asistenciauco.aplication.interactor.InteractorConRetorno;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response.EstudianteResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ListarEstudiantesPorGrupoInteractor extends InteractorConRetorno<UUID, List<EstudianteResponseDTO>> {
    // El parámetro es el UUID del grupo y retorna la lista de estudiantes en DTO
}
