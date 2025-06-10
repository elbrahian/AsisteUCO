package co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.impl;

import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel.CatalogoMensajes;
import org.springframework.stereotype.Service;

import co.edu.uco.asistenciauco.aplication.exception.AsistenciaValidationException;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.RegistrarAsistenciaInteractor;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.request.RegistrarAsistenciaRequestDTO;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response.RegistrarAsistenciaResponseDTO;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.mappers.AsistenciaMapperDomain;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.RegistrarAsistenciaUseCase;

@Service
public class RegistrarAsistenciaInteractorImpl implements RegistrarAsistenciaInteractor {
	private final RegistrarAsistenciaUseCase registrarAsistenciaUseCase;

	public RegistrarAsistenciaInteractorImpl(RegistrarAsistenciaUseCase registrarAsistenciaUseCase) {
		this.registrarAsistenciaUseCase = registrarAsistenciaUseCase;
	}

	@Override
	public RegistrarAsistenciaResponseDTO ejecutar(RegistrarAsistenciaRequestDTO dto) {
		try {
			var domain = AsistenciaMapperDomain.toDomain(dto);
			registrarAsistenciaUseCase.ejecutar(domain);
			return new RegistrarAsistenciaResponseDTO(CatalogoMensajes.ASISTENCIA_REGISTRADA.getMensaje());
		} catch (AsistenciaValidationException e) {
			throw new AsistenciaValidationException(e.getMessage());
		}
	}
}