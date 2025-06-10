package co.edu.uco.asistenciauco.aplication.usecase.asistencia.validator;

import java.util.UUID;

import co.edu.uco.asistenciauco.aplication.outport.repository.AsistenciaRepository;
import co.edu.uco.asistenciauco.aplication.usecase.validator.ValidationResultVO;
import co.edu.uco.asistenciauco.aplication.usecase.validator.Validator;
import org.springframework.stereotype.Service;


@Service
public class ValidarQueExisteAsistenciaPorSesion implements Validator<ValidarQueExisteAsistenciaPorSesion.ParametrosValidacion, ValidationResultVO> {
	private final AsistenciaRepository repository;

	public ValidarQueExisteAsistenciaPorSesion(AsistenciaRepository repository) {
		this.repository = repository;
	}

	@Override
	public ValidationResultVO validate(ParametrosValidacion parametros) {
		ValidationResultVO resultadoValidacion = new ValidationResultVO();

		if (parametros == null) {
			resultadoValidacion.agregarMensaje("Los parámetros de validación no pueden ser nulos.");
			return resultadoValidacion;
		}

		if (parametros.getIdSesion() == null) {
			resultadoValidacion.agregarMensaje("El identificador de la sesión no puede ser nulo.");
			return resultadoValidacion;
		}

		if (parametros.getIdEstudianteGrupo() == null) {
			resultadoValidacion.agregarMensaje("El identificador del estudiante-grupo no puede ser nulo.");
			return resultadoValidacion;
		}

		if (repository.existsBySesion_idAndEstudianteGrupo_id(parametros.getIdSesion(), parametros.getIdEstudianteGrupo())) {
			resultadoValidacion.agregarMensaje("Ya existe una asistencia registrada para este estudiante en esta sesión.");
		}

		return resultadoValidacion;
	}

	/**
	 * Clase para encapsular los parámetros de validación
	 */
	public static class ParametrosValidacion {
		private final UUID idSesion;
		private final UUID idEstudianteGrupo;

		public ParametrosValidacion(UUID idSesion, UUID idEstudianteGrupo) {
			this.idSesion = idSesion;
			this.idEstudianteGrupo = idEstudianteGrupo;
		}

		public UUID getIdSesion() {
			return idSesion;
		}

		public UUID getIdEstudianteGrupo() {
			return idEstudianteGrupo;
		}
	}
}