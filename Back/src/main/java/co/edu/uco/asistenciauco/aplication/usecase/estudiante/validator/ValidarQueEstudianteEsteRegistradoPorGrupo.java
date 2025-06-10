package co.edu.uco.asistenciauco.aplication.usecase.estudiante.validator;

import java.util.UUID;

import co.edu.uco.asistenciauco.aplication.outport.repository.EstudianteGrupoRepository;
import co.edu.uco.asistenciauco.aplication.usecase.validator.ValidationResultVO;
import co.edu.uco.asistenciauco.aplication.usecase.validator.Validator;
import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel.CatalogoMensajes;
import org.springframework.stereotype.Service;

@Service
public class ValidarQueEstudianteEsteRegistradoPorGrupo implements Validator<UUID, ValidationResultVO> {
	
	private final EstudianteGrupoRepository estudianteGrupoRepository;
	
	public ValidarQueEstudianteEsteRegistradoPorGrupo(EstudianteGrupoRepository estudianteGrupoRepository) {
		this.estudianteGrupoRepository = estudianteGrupoRepository;
	}
	
	@Override
	public ValidationResultVO validate(UUID estudianteGrupoId) {
		var resultadoValidacion = new ValidationResultVO();
		
		if (!estudianteGrupoRepository.existsById(estudianteGrupoId)) {
			resultadoValidacion.agregarMensaje(CatalogoMensajes.ESTUDIANTE_NO_REGISTRADO.getMensaje());
		}
		
		return resultadoValidacion;
	}

}
