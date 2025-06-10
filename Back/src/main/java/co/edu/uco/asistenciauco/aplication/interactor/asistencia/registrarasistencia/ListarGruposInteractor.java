package co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia;

import co.edu.uco.asistenciauco.aplication.interactor.InteractorConRetorno;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response.GrupoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ListarGruposInteractor extends InteractorConRetorno<UUID, List<GrupoResponseDTO>>{
}
