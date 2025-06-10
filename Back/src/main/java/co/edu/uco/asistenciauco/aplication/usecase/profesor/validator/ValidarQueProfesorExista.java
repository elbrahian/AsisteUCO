package co.edu.uco.asistenciauco.aplication.usecase.profesor.validator;

import java.util.UUID;

import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel.CatalogoMensajes;
import org.springframework.stereotype.Service;

import co.edu.uco.asistenciauco.aplication.outport.repository.ProfesorRepository;
import co.edu.uco.asistenciauco.aplication.usecase.validator.ValidationResultVO;
import co.edu.uco.asistenciauco.aplication.usecase.validator.Validator;

@Service
public class ValidarQueProfesorExista implements Validator<UUID, ValidationResultVO>{

    private ProfesorRepository profesorRepository;

    public ValidarQueProfesorExista(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    @Override
    public ValidationResultVO validate(UUID id) {
        var resultadoValidacion = new ValidationResultVO();

        if (!profesorRepository.existsById(id)) {
            //TODO: No se pueden quemar mensajes. Debe estar en el catalogo de mensajes
            resultadoValidacion.agregarMensaje(CatalogoMensajes.NO_PROFESOR_IDENTIFICADOR.getMensaje() + id);
        }

        return resultadoValidacion;
    }

}