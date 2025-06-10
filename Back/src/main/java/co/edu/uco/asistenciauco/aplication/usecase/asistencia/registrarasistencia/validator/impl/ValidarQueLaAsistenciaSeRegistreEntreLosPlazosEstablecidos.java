package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.validator.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.asistenciauco.aplication.outport.repository.SesionRepository;
import co.edu.uco.asistenciauco.aplication.usecase.validator.ValidationResultVO;
import co.edu.uco.asistenciauco.aplication.usecase.validator.Validator;

@Service
public final class ValidarQueLaAsistenciaSeRegistreEntreLosPlazosEstablecidos implements Validator<UUID, ValidationResultVO> {
    private final SesionRepository sesionRepository;
    private static final int MINUTOS_ANTES = 60;
    private static final int MINUTOS_DESPUES = 60;

    public ValidarQueLaAsistenciaSeRegistreEntreLosPlazosEstablecidos(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    @Override
    public ValidationResultVO validate(UUID sesionId) {
        var resultadoValidacion = new ValidationResultVO();
        
        var sesionOptional = sesionRepository.findById(sesionId);
        if (sesionOptional.isEmpty()) {
            resultadoValidacion.agregarMensaje("La sesión especificada no existe.");
            return resultadoValidacion;
        }

        var sesion = sesionOptional.get();
        var fechaHoraSesion = sesion.getFechaHora().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        var ahora = LocalDateTime.now();
        var inicioPermitido = fechaHoraSesion.minusMinutes(MINUTOS_ANTES);
        var finPermitido = fechaHoraSesion.plusMinutes(MINUTOS_DESPUES);

        if (ahora.isBefore(inicioPermitido) || ahora.isAfter(finPermitido)) {
            resultadoValidacion.agregarMensaje(
                String.format("La asistencia solo puede ser registrada desde %d minutos antes hasta %d minutos después de la hora de la sesión.",
                    MINUTOS_ANTES, MINUTOS_DESPUES)
            );
        }
        return resultadoValidacion;
    }
}