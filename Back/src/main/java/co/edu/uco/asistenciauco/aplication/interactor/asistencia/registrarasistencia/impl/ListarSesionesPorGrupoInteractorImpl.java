package co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.impl;

import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.ListarSesionesPorGrupoInteractor;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response.SesionResponseDTO;
import co.edu.uco.asistenciauco.aplication.outport.entity.SesionEntity;
import co.edu.uco.asistenciauco.aplication.outport.repository.SesionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListarSesionesPorGrupoInteractorImpl implements ListarSesionesPorGrupoInteractor {
    private final SesionRepository sesionRepository;

    public ListarSesionesPorGrupoInteractorImpl(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }
@Override
public List<SesionResponseDTO> ejecutar(UUID grupoId) {
    List<SesionEntity> sesiones = sesionRepository.findByGrupoId(grupoId);

    return sesiones.stream()
            .<SesionResponseDTO>map(sesion -> new SesionResponseDTO(
                    sesion.getId(),
                    sesion.getFechaHora().toInstant()
                            .atZone(java.time.ZoneId.systemDefault())
                            .toLocalDateTime(),
                    sesion.getGrupo().getId()))
            .collect(Collectors.toList());
}
}
