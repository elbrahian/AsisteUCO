package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.impl;

import co.edu.uco.asistenciauco.aplication.outport.entity.ProfesorEntity;
import co.edu.uco.asistenciauco.aplication.outport.repository.ProfesorRepository;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.BuscarCorreoProfesorUseCase;
import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel.CatalogoMensajes;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class BuscarCorreoProfesorUseCaseImpl implements BuscarCorreoProfesorUseCase {
    private final ProfesorRepository repository;
    
    public BuscarCorreoProfesorUseCaseImpl(ProfesorRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public String ejecutar(UUID profesorId) {
        return repository.findById(profesorId)
                .map(ProfesorEntity::getCorreo)
                .orElseThrow(() -> new RuntimeException(CatalogoMensajes.PROFESOR_NO_ENCONTRADO.getMensaje()));
    }
} 