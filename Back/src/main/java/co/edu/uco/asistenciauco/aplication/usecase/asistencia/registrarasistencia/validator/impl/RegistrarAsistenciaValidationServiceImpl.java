package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.validator.impl;

import co.edu.uco.asistenciauco.aplication.outport.repository.EstudianteGrupoRepository;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain.AsistenciaDomain;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.validator.RegistrarAsistenciaValidationService;
import co.edu.uco.asistenciauco.aplication.usecase.asistencia.validator.ValidarQueExisteAsistenciaPorSesion;
import co.edu.uco.asistenciauco.aplication.usecase.estudiante.validator.ValidarQueEstudianteEsteRegistradoPorGrupo;
import co.edu.uco.asistenciauco.aplication.usecase.estudiante.validator.ValidarQueEstudianteExista;
import co.edu.uco.asistenciauco.aplication.usecase.estudiante.validator.ValidarQueEstudianteNoTengaMateriaCancelada;
import co.edu.uco.asistenciauco.aplication.usecase.grupo.validator.ValidarQueGrupoEsteActivo;
import co.edu.uco.asistenciauco.aplication.usecase.profesor.validator.ValidarQueProfesorEsteAsignadoAlGrupo;
import co.edu.uco.asistenciauco.aplication.usecase.profesor.validator.ValidarQueProfesorExista;
import co.edu.uco.asistenciauco.aplication.usecase.sesion.validator.ValidarQueSesionExista;
import co.edu.uco.asistenciauco.aplication.usecase.validator.ValidationResultVO;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Supplier;


@Service
public class RegistrarAsistenciaValidationServiceImpl implements RegistrarAsistenciaValidationService {
    private final ValidarQueSesionExista sesionExiste;
    private final ValidarQueGrupoEsteActivo grupoEsteActivo;
    private final ValidarQueEstudianteEsteRegistradoPorGrupo estudianteGrupoValidador;
    private final ValidarQueExisteAsistenciaPorSesion existeAsistenciaPorSesion;
    private final ValidarQueProfesorExista profesorValidador;
    private final ValidarQueProfesorEsteAsignadoAlGrupo profesorGrupoValidador;
    private final ValidarQueLaAsistenciaSeRegistreEntreLosPlazosEstablecidos plazosValidador;
    private final ValidarQueEstudianteNoTengaMateriaCancelada materiaCanceladaValidador;

    private final EstudianteGrupoRepository estudianteGrupoRepository;

    public RegistrarAsistenciaValidationServiceImpl(
            ValidarQueEstudianteExista estudianteExiste,
            ValidarQueSesionExista sesionExiste,
            ValidarQueGrupoEsteActivo grupoEsteActivo,
            ValidarQueEstudianteEsteRegistradoPorGrupo estudianteGrupoValidador, co.edu.uco.asistenciauco.aplication.usecase.asistencia.validator.ValidarQueExisteAsistenciaPorSesion existeAsistenciaPorSesion,
            ValidarQueProfesorExista profesorValidador,
            ValidarQueProfesorEsteAsignadoAlGrupo profesorGrupoValidador,
            ValidarQueLaAsistenciaSeRegistreEntreLosPlazosEstablecidos plazosValidador,
            ValidarQueEstudianteNoTengaMateriaCancelada materiaCanceladaValidador,
            EstudianteGrupoRepository estudianteGrupoRepository) {

        this.sesionExiste = sesionExiste;
        this.grupoEsteActivo = grupoEsteActivo;
        this.estudianteGrupoValidador = estudianteGrupoValidador;
        this.existeAsistenciaPorSesion = existeAsistenciaPorSesion;
        this.profesorValidador = profesorValidador;
        this.profesorGrupoValidador = profesorGrupoValidador;
        this.plazosValidador = plazosValidador;
        this.materiaCanceladaValidador = materiaCanceladaValidador;
        this.estudianteGrupoRepository = estudianteGrupoRepository;
    }

    @Override
    public ValidationResultVO validar(AsistenciaDomain dominio) {
        var resultadoFinal = new ValidationResultVO();

        try {
            // 1. Validar datos requeridos
            var validacionDatos = validarDatosRequeridos(dominio);
            if (!validacionDatos.isValidacionCorrecta()) {
                resultadoFinal.agregarMensajes(validacionDatos.getMensajes());
                return resultadoFinal; // Si faltan datos básicos, no continuar
            }

            // Obtener información necesaria para las validaciones
            UUID estudianteGrupoId = dominio.getEstudiantePorGrupoDomain().getId();
            var estudianteGrupo = estudianteGrupoRepository.findById(estudianteGrupoId);

            if (estudianteGrupo.isEmpty()) {
                resultadoFinal.agregarMensaje("No se encontró la relación estudiante-grupo especificada.");
                return resultadoFinal;
            }

            UUID grupoId = estudianteGrupo.get().getGrupo().getId();
            UUID estudianteId = estudianteGrupo.get().getEstudiante().getId();

            // 2. Validar sesión
            var validacionSesion = ejecutarValidacionConCapturadeExcepciones(() -> validarSesion(dominio.getSesion().getId()));
            resultadoFinal.agregarMensajes(validacionSesion.getMensajes());

            // 3. Validar profesor
            var validacionProfesor = ejecutarValidacionConCapturadeExcepciones(() -> validarProfesor(dominio.getProfesor().getId()));
            resultadoFinal.agregarMensajes(validacionProfesor.getMensajes());

            // 4. Validar grupo activo
            var validacionGrupo = ejecutarValidacionConCapturadeExcepciones(() -> validarGrupoActivo(estudianteGrupoId));
            resultadoFinal.agregarMensajes(validacionGrupo.getMensajes());

            // 5. Validar profesor asignado al grupo
            var validacionProfesorGrupo = ejecutarValidacionConCapturadeExcepciones(() -> validarProfesorAsignadoAlGrupo(dominio.getProfesor().getId(), grupoId));
            resultadoFinal.agregarMensajes(validacionProfesorGrupo.getMensajes());

            // 6. Validar asistencia no existente
            var validacionAsistenciaNoExistente = ejecutarValidacionConCapturadeExcepciones(() -> validarAsistenciaNoExistente(dominio.getSesion().getId(), estudianteGrupoId));
            resultadoFinal.agregarMensajes(validacionAsistenciaNoExistente.getMensajes());

            // 7. Validar plazos permitidos
            var validacionPlazos = ejecutarValidacionConCapturadeExcepciones(() -> validarPlazosPermitidos(dominio.getSesion().getId()));
            resultadoFinal.agregarMensajes(validacionPlazos.getMensajes());

            // 8. Validar estudiante
            var validacionEstudiante = ejecutarValidacionConCapturadeExcepciones(() -> validarEstudiante(estudianteGrupoId, estudianteId, grupoId));
            resultadoFinal.agregarMensajes(validacionEstudiante.getMensajes());

        } catch (Exception e) {
            resultadoFinal.agregarMensaje("Error inesperado durante la validación: " + e.getMessage());
        }

        return resultadoFinal;
    }

    private ValidationResultVO ejecutarValidacionConCapturadeExcepciones(Supplier<ValidationResultVO> validacionSupplier) {
        try {
            return validacionSupplier.get();
        } catch (Exception e) {
            var resultado = new ValidationResultVO();
            resultado.agregarMensaje("Error en validación: " + e.getMessage());
            return resultado;
        }
    }

    public ValidationResultVO validarDatosRequeridos(AsistenciaDomain dominio) {
        var resultado = new ValidationResultVO();

        if (dominio == null) {
            resultado.agregarMensaje("Los datos de la asistencia son requeridos");
            return resultado;
        }

        if (dominio.getSesion() == null || dominio.getSesion().getId() == null) {
            resultado.agregarMensaje("La sesión es requerida");
        }

        if (dominio.getProfesor() == null || dominio.getProfesor().getId() == null) {
            resultado.agregarMensaje("El profesor es requerido");
        }

        if (dominio.getEstudiantePorGrupoDomain() == null || dominio.getEstudiantePorGrupoDomain().getId() == null) {
            resultado.agregarMensaje("Los datos del estudiante y grupo son requeridos");
        }
        return resultado;
    }

    public ValidationResultVO validarSesion(UUID sesionId) {
        return sesionExiste.validate(sesionId);
    }

    public ValidationResultVO validarProfesor(UUID profesorId) {
        return profesorValidador.validate(profesorId);
    }

    public ValidationResultVO validarGrupoActivo(UUID estudianteGrupoId) {
        return grupoEsteActivo.validate(estudianteGrupoId);
    }

    public ValidationResultVO validarProfesorAsignadoAlGrupo(UUID profesorId, UUID grupoId) {
        var input = new ValidarQueProfesorEsteAsignadoAlGrupo.Input(profesorId, grupoId);
        return profesorGrupoValidador.validate(input);
    }

    public ValidationResultVO validarAsistenciaNoExistente(UUID sesionId, UUID estudianteGrupoId) {
        return existeAsistenciaPorSesion.validate(new ValidarQueExisteAsistenciaPorSesion.ParametrosValidacion(sesionId, estudianteGrupoId));
    }

    public ValidationResultVO validarPlazosPermitidos(UUID sesionId) {
        return plazosValidador.validate(sesionId);
    }

    public ValidationResultVO validarEstudiante(UUID estudianteGrupoId, UUID estudianteId, UUID grupoId) {
        var resultado = new ValidationResultVO();

        // 1. Validar que el estudiante-grupo exista y esté activo
        var validacionEstudianteGrupo = estudianteGrupoValidador.validate(estudianteGrupoId);
        resultado.agregarMensajes(validacionEstudianteGrupo.getMensajes());

        // 2. Validar que el estudiante no tenga la materia cancelada
        var inputMateriaCancelada = new ValidarQueEstudianteNoTengaMateriaCancelada.Input(estudianteId, grupoId);
        var validacionMateriaCancelada = materiaCanceladaValidador.validate(inputMateriaCancelada);
        resultado.agregarMensajes(validacionMateriaCancelada.getMensajes());

        return resultado;
    }
} 