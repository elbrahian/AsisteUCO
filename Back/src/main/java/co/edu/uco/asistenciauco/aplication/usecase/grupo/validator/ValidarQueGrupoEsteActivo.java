package co.edu.uco.asistenciauco.aplication.usecase.grupo.validator;

import co.edu.uco.asistenciauco.aplication.outport.entity.GrupoEntity;
import co.edu.uco.asistenciauco.aplication.outport.repository.GrupoRepository;
import co.edu.uco.asistenciauco.aplication.outport.repository.EstudianteGrupoRepository;
import co.edu.uco.asistenciauco.aplication.usecase.validator.ValidationResultVO;
import co.edu.uco.asistenciauco.aplication.usecase.validator.Validator;
import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel.CatalogoMensajes;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ValidarQueGrupoEsteActivo implements Validator<UUID, ValidationResultVO> {
    private final GrupoRepository grupoRepository;
    private final EstudianteGrupoRepository estudianteGrupoRepository;

    public ValidarQueGrupoEsteActivo(GrupoRepository grupoRepository, EstudianteGrupoRepository estudianteGrupoRepository) {
        this.grupoRepository = grupoRepository;
        this.estudianteGrupoRepository = estudianteGrupoRepository;
    }

    @Override
    public ValidationResultVO validate(UUID estudianteGrupoId) {
        var resultadoValidacion = new ValidationResultVO();
        
        // Primero obtenemos el grupo a través de estudianteGrupo
        var estudianteGrupo = estudianteGrupoRepository.findById(estudianteGrupoId);
        
        if (estudianteGrupo.isPresent()) {
            var grupoId = estudianteGrupo.get().getGrupo().getId();
            Optional<GrupoEntity> grupo = grupoRepository.findById(grupoId);
            
            grupo.ifPresent(elem -> {
                if (!elem.isEstado()) {
                    resultadoValidacion.agregarMensaje(CatalogoMensajes.GRUPO_NO_ACTIVO.getMensaje());
                }
            });
        } else {
            resultadoValidacion.agregarMensaje(CatalogoMensajes.NO_RELACION_ESTUDIANTE_GRUPO.getMensaje());
        }
        
        return resultadoValidacion;
    }
}
