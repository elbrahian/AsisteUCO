package co.edu.uco.asistenciauco.aplication.usecase.profesor.validator;

import java.util.UUID;

import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel.CatalogoMensajes;
import org.springframework.stereotype.Service;

import co.edu.uco.asistenciauco.aplication.outport.repository.GrupoRepository;
import co.edu.uco.asistenciauco.aplication.usecase.validator.ValidationResultVO;
import co.edu.uco.asistenciauco.aplication.usecase.validator.Validator;

@Service
public class ValidarQueProfesorEsteAsignadoAlGrupo implements Validator<ValidarQueProfesorEsteAsignadoAlGrupo.Input, ValidationResultVO> {
    
    private final GrupoRepository grupoRepository;

    public ValidarQueProfesorEsteAsignadoAlGrupo(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @Override
    public ValidationResultVO validate(Input input) {
        var resultadoValidacion = new ValidationResultVO();
        if (!grupoRepository.existsByIdAndProfesorId(input.getGrupoId(), input.getProfesorId())) {
            resultadoValidacion.agregarMensaje(CatalogoMensajes.PROFESOR_NO_ASIGNADO_GRUPO.getMensaje());
        }
        
        return resultadoValidacion;
    }

    public static class Input {
        private final UUID profesorId;
        private final UUID grupoId;

        public Input(UUID profesorId, UUID grupoId) {
            this.profesorId = profesorId;
            this.grupoId = grupoId;
        }

        public UUID getProfesorId() {
            return profesorId;
        }

        public UUID getGrupoId() {
            return grupoId;
        }
    }
} 