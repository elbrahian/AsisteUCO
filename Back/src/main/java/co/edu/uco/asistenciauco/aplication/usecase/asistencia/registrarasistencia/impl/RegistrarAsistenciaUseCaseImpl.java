package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.impl;

import co.edu.uco.asistenciauco.aplication.exception.AsistenciaValidationException;
import co.edu.uco.asistenciauco.aplication.outport.entity.EstudianteEntity;
import co.edu.uco.asistenciauco.aplication.outport.mail.SendMailPort;
import co.edu.uco.asistenciauco.aplication.outport.repository.AsistenciaRepository;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.mappers.AsistenciaMapperEntities;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.BuscarCorreoEstudianteUseCase;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.BuscarCorreoProfesorUseCase;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain.EstudianteDomain;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.validator.RegistrarAsistenciaValidationService;
import co.edu.uco.asistenciauco.crosscutting.exceptions.AplicationException;
import co.edu.uco.asistenciauco.crosscutting.exceptions.enums.Layer;
import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel.CatalogoMensajes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.RegistrarAsistenciaUseCase;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain.AsistenciaDomain;
import co.edu.uco.asistenciauco.aplication.outport.entity.AsistenciaEntity;

import java.util.UUID;

@Service
public class RegistrarAsistenciaUseCaseImpl implements RegistrarAsistenciaUseCase {
    private final RegistrarAsistenciaValidationService validationService;
    private final BuscarCorreoEstudianteUseCase buscarCorreoEstudianteUseCase;
    private final BuscarCorreoProfesorUseCase buscarCorreoProfesorUseCase;
    private final AsistenciaRepository asistenciaRepository;
    private final SendMailPort mailPort;

    public RegistrarAsistenciaUseCaseImpl(
            RegistrarAsistenciaValidationService validationService,
            BuscarCorreoEstudianteUseCase buscarCorreoEstudianteUseCase,
            BuscarCorreoProfesorUseCase buscarCorreoProfesorUseCase,
            AsistenciaRepository asistenciaRepository,
            SendMailPort mailPort) {
        
        this.validationService = validationService;
        this.buscarCorreoEstudianteUseCase = buscarCorreoEstudianteUseCase;
        this.buscarCorreoProfesorUseCase = buscarCorreoProfesorUseCase;
        this.asistenciaRepository = asistenciaRepository;
        this.mailPort = mailPort;
    }
    
    @Override
    @Transactional
    public void ejecutar(AsistenciaDomain dominio) {
        // 1. Validar todos los aspectos del registro de asistencia usando el servicio centralizado
        var resultadoValidacion = validationService.validar(dominio);
        
        // 2. Si hay errores de validación, lanzar excepción
        if (!resultadoValidacion.isValidacionCorrecta()) {
            throw new AsistenciaValidationException(resultadoValidacion.getMensajes());
        }
        
        // 3. Si todas las validaciones son correctas, registrar la asistencia
        AsistenciaEntity asistenciaEntity = registrarAsistenciaEstudiante(dominio);
        
        // 4. Enviar notificación si es necesario (desde el correo del profesor)
        if (!asistenciaEntity.isAsistio()) {
            dominio.getEstudiantePorGrupoDomain().getEstudiante()
                    .setId(asistenciaEntity.getEstudianteGrupo().getEstudiante().getId());
            enviarNotificacionInasistencia(dominio);
        }
    }

    private AsistenciaEntity registrarAsistenciaEstudiante(AsistenciaDomain asistenciaDomain) {
        try {
            var asistenciaEntity = AsistenciaMapperEntities.toEntity(asistenciaDomain);
            return asistenciaRepository.save(asistenciaEntity);
        } catch (Exception e) {
            throw new AplicationException(CatalogoMensajes.ERROR_ASISTENCIA_BASE_DE_DATOS.getMensaje(), e.getMessage(), e, Layer.BUSINESSLOGIC); //"Error al guardar la asistencia en la base de datos"
        }
    }

    private void enviarNotificacionInasistencia(AsistenciaDomain dominio) {
        try {
            var correoEstudiante = obtenerCorreoEstudiante(dominio.getEstudiantePorGrupoDomain().getEstudiante());
            var correoProfesor = obtenerCorreoProfesor(dominio.getProfesor().getId());
            
            mailPort.enviarCorreo(
                correoProfesor,
                correoEstudiante,
                "Notificación de Inasistencia",
                "Estimado estudiante, se ha registrado su inasistencia en la sesión. Por favor, justifique su ausencia si es necesario."
            );
        } catch (Exception e) {
            throw new AsistenciaValidationException(CatalogoMensajes.ERROR_NOTIFICACION_INASISTENCIA.getMensaje() + e.getMessage());
        }
    }

    private String obtenerCorreoEstudiante(EstudianteDomain estudiante) {
        return buscarCorreoEstudianteUseCase.ejecutar(estudiante.getId());
    }
    
    private String obtenerCorreoProfesor(UUID profesorId) {
        return buscarCorreoProfesorUseCase.ejecutar(profesorId);
    }
}