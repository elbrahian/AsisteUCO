package co.edu.uco.asistenciauco.aplication.usecase.grupo.impl;

import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.ListarEstudiantesPorGrupoInteractor;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response.EstudianteResponseDTO;
import co.edu.uco.asistenciauco.aplication.outport.entity.EstudianteEntity;
import co.edu.uco.asistenciauco.aplication.outport.entity.EstudiantePorGrupoEntity;
import co.edu.uco.asistenciauco.aplication.outport.repository.EstudianteGrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListarEstudiantesPorGrupo implements ListarEstudiantesPorGrupoInteractor {

    private final EstudianteGrupoRepository estudianteGrupoRepository;

    public ListarEstudiantesPorGrupo(EstudianteGrupoRepository estudianteGrupoRepository) {
        this.estudianteGrupoRepository = estudianteGrupoRepository;
    }

    @Override
    public List<EstudianteResponseDTO> ejecutar(UUID grupoId) {
        List<EstudiantePorGrupoEntity> entidades = estudianteGrupoRepository.findByGrupoId(grupoId);

        return entidades.stream()
                .map(eg -> {
                    EstudianteEntity e = eg.getEstudiante();
                    return new EstudianteResponseDTO(e.getId(), e.getNumeroIdentificacion(), e.getNombresCompletos());
                })
                .collect(Collectors.toList());
    }
}
