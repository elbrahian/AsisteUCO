package co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia;

import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.request.RegistrarAsistenciaRequestDTO;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response.RegistrarAsistenciaResponseDTO;

public interface RegistrarAsistenciaInteractor {
	RegistrarAsistenciaResponseDTO ejecutar(RegistrarAsistenciaRequestDTO dto);
}