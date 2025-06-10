package co.edu.uco.asistenciauco.aplication.usecase.asistencia.mappers;

import co.edu.uco.asistenciauco.aplication.outport.entity.AsistenciaEntity;
import co.edu.uco.asistenciauco.aplication.outport.entity.EstudiantePorGrupoEntity;
import co.edu.uco.asistenciauco.aplication.outport.entity.SesionEntity;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain.AsistenciaDomain;
import co.edu.uco.asistenciauco.crosscutting.helpers.UUIDHelper;

public class AsistenciaMapperEntities {
    public static AsistenciaEntity toEntity(AsistenciaDomain domain) {
        // Crear EstudianteGrupoEntity con el ID
        EstudiantePorGrupoEntity estudianteGrupo = new EstudiantePorGrupoEntity();
        estudianteGrupo.setId(domain.getEstudiantePorGrupoDomain().getId());

        // Crear SesionEntity con el ID
        SesionEntity sesion = new SesionEntity();
        sesion.setId(domain.getSesion().getId());

        // Crear entidad Asistencia
        AsistenciaEntity entity = new AsistenciaEntity();
        entity.setId(UUIDHelper.generate());
        entity.setEstudianteGrupo(estudianteGrupo);
        entity.setSesion(sesion);
        entity.setAsistio(domain.getEstudiantePorGrupoDomain().getEstudiante().isAsistio());

        return entity;
    }
}
