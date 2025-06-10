package co.edu.uco.asistenciauco.aplication.usecase.estudiante.validator;

import java.util.UUID;

import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel.CatalogoMensajes;
import org.springframework.stereotype.Service;

import co.edu.uco.asistenciauco.aplication.outport.repository.EstudianteGrupoRepository;
import co.edu.uco.asistenciauco.aplication.usecase.validator.ValidationResultVO;
import co.edu.uco.asistenciauco.aplication.usecase.validator.Validator;

@Service
public class ValidarQueEstudianteNoTengaMateriaCancelada implements Validator<ValidarQueEstudianteNoTengaMateriaCancelada.Input, ValidationResultVO> {
    
    private final EstudianteGrupoRepository estudianteGrupoRepository;

    public ValidarQueEstudianteNoTengaMateriaCancelada(EstudianteGrupoRepository estudianteGrupoRepository) {
        this.estudianteGrupoRepository = estudianteGrupoRepository;
    }

    @Override
    public ValidationResultVO validate(Input input) {
        var resultadoValidacion = new ValidationResultVO();
        
        var estudianteGrupo = estudianteGrupoRepository.findByEstudianteIdAndGrupoId(input.getEstudianteId(), input.getGrupoId());
        if (estudianteGrupo.isPresent() && estudianteGrupo.get().isCancelado()) {
            resultadoValidacion.agregarMensaje(CatalogoMensajes.ESTUDIANTE_TIENE_MATERIA_CANCELADA.getMensaje());
        }
        
        return resultadoValidacion;
    }

    public static class Input {
        private final UUID estudianteId;
        private final UUID grupoId;

        public Input(UUID estudianteId, UUID grupoId) {
            this.estudianteId = estudianteId;
            this.grupoId = grupoId;
        }

        public UUID getEstudianteId() {
            return estudianteId;
        }

        public UUID getGrupoId() {
            return grupoId;
        }
    }
} 