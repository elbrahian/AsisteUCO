package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.validator;


import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain.AsistenciaDomain;
import co.edu.uco.asistenciauco.aplication.usecase.validator.ValidationResultVO;

public interface RegistrarAsistenciaValidationService {
    ValidationResultVO validar(AsistenciaDomain domain);
} 