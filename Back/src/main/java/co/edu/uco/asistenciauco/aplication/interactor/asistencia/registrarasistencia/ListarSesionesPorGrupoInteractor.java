package co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia;

import co.edu.uco.asistenciauco.aplication.interactor.InteractorConRetorno;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response.SesionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ListarSesionesPorGrupoInteractor extends InteractorConRetorno<UUID, List<SesionResponseDTO>> {
}
