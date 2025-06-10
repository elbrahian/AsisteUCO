package co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.mappers;

import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.request.RegistrarAsistenciaRequestDTO;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain.*;

public class AsistenciaMapperDomain {
    public static AsistenciaDomain toDomain(RegistrarAsistenciaRequestDTO dto) {
        return new AsistenciaDomain(
            new SesionDomain(dto.getSesion()),
            new ProfesorDomain(dto.getProfesor()),
            new EstudiantePorGrupoDomain(
                dto.getEstudianteGrupo(),
                null,
                new EstudianteDomain(null, dto.isAsistio())
            )
        );
    }
}