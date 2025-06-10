package co.edu.uco.asistenciauco.aplication.usecase.sesion.validator;

import java.util.UUID;

import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel.CatalogoMensajes;
import org.springframework.stereotype.Service;

import co.edu.uco.asistenciauco.aplication.outport.repository.SesionRepository;
import co.edu.uco.asistenciauco.aplication.usecase.validator.ValidationResultVO;
import co.edu.uco.asistenciauco.aplication.usecase.validator.Validator;

@Service
public class ValidarQueSesionExista implements Validator<UUID, ValidationResultVO>{
	
	private SesionRepository sesionRepository;

	public ValidarQueSesionExista(SesionRepository sesionRepository) {
		this.sesionRepository = sesionRepository;
	}

	@Override
	public ValidationResultVO validate(UUID id) {
		
		var resultadoValidacion = new ValidationResultVO();
		
		if (!sesionRepository.existsById(id)) {
			//TODO: No se pueden quemar mensajes. Debe estar en el catalogo de mensajes
			resultadoValidacion.agregarMensaje(CatalogoMensajes.NO_SESION_IDENTIFICADOR.getMensaje() + id);
		}
		
		return resultadoValidacion;
	}
	
}
