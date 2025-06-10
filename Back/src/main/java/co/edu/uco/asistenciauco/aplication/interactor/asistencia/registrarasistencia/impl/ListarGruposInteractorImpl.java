package co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.impl;

import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.ListarGruposInteractor;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response.GrupoResponseDTO;
import co.edu.uco.asistenciauco.aplication.outport.entity.GrupoEntity;
import co.edu.uco.asistenciauco.aplication.outport.repository.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListarGruposInteractorImpl implements ListarGruposInteractor {
    private final GrupoRepository grupoRepository;

    public ListarGruposInteractorImpl(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @Override
    public List<GrupoResponseDTO> ejecutar(UUID dto) {
        List<GrupoEntity> entidades = grupoRepository.findAll();

        return entidades.stream()
                .map(grupo -> new GrupoResponseDTO(
                        grupo.getId(),
                        grupo.getProfesor().getNombresCompletos(),
                        grupo.getProfesor().getId(),
                        grupo.getMateria().getNombre(),
                        grupo.getCantidadEstudiantes()))
                .collect(Collectors.toList());
    }
}
