package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.impl;

import co.edu.uco.asistenciauco.aplication.outport.entity.EstudianteEntity;
import co.edu.uco.asistenciauco.aplication.outport.repository.EstudianteRepository;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.BuscarCorreoEstudianteUseCase;
import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel.CatalogoMensajes;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class BuscarCorreoEstudianteUseCaseImpl implements BuscarCorreoEstudianteUseCase {
    private final EstudianteRepository repository;
    public BuscarCorreoEstudianteUseCaseImpl(EstudianteRepository repository) {
        this.repository = repository;
    }
    @Override
    public String ejecutar(UUID idEstudiante) {
        return repository.findById(idEstudiante)
                .map(EstudianteEntity::getCorreo)
                .orElseThrow(() -> new RuntimeException(CatalogoMensajes.ESTUDIANTE_NO_ENCONTRADO.getMensaje()));
    }
}