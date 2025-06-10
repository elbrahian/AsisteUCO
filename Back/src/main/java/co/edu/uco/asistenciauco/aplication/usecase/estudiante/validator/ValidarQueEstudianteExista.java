package co.edu.uco.asistenciauco.aplication.usecase.estudiante.validator;

import java.util.UUID;

import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel.CatalogoMensajes;
import org.springframework.stereotype.Service;

import co.edu.uco.asistenciauco.aplication.outport.repository.EstudianteRepository;
import co.edu.uco.asistenciauco.aplication.usecase.validator.ValidationResultVO;
import co.edu.uco.asistenciauco.aplication.usecase.validator.Validator;

@Service
public class ValidarQueEstudianteExista implements Validator<UUID, ValidationResultVO>{
	
	private EstudianteRepository estudianteRepository;

	public ValidarQueEstudianteExista(EstudianteRepository estudianteRepository) {
		this.estudianteRepository = estudianteRepository;
	}

	@Override
	public ValidationResultVO validate(UUID id) {
		// TODO Auto-generated method stub
		
		var resultadoValidacion = new ValidationResultVO();
		
		if (!estudianteRepository.existsById(id)) {
			//TODO: No se pueden quemar mensajes. Debe estar en el catalogo de mensajes
			resultadoValidacion.agregarMensaje(CatalogoMensajes.ESTUDIANTE_NO_EXISTE_CON_ID.getMensaje() + id);
		}
		
		return resultadoValidacion;
	}
	
}
