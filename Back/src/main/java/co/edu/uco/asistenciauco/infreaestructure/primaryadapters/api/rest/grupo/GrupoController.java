package co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.grupo;

import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.ListarEstudiantesPorGrupoInteractor;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.ListarGruposInteractor;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.ListarSesionesPorGrupoInteractor;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response.EstudianteResponseDTO;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response.GrupoResponseDTO;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response.SesionResponseDTO;
import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel.CatalogoMensajes;
import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@CrossOrigin(originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/grupos")
public class GrupoController {
    private final ListarGruposInteractor listarGruposInteractor;
    private final ListarEstudiantesPorGrupoInteractor listarEstudiantesPorGrupoInteractor;
    private final ListarSesionesPorGrupoInteractor listarSesionesPorGrupoInteractor;

    @GetMapping
    public ResponseEntity<ApiResponse<List<GrupoResponseDTO>>> listarGrupos() {
        try {
            List<GrupoResponseDTO> grupos = listarGruposInteractor.ejecutar(null);
            if (grupos.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse<>(
                    true,
                        CatalogoMensajes.GRUPOS_NO_ENCONTRADOS.getMensaje(),
                    grupos
                ));
            }
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                CatalogoMensajes.GRUPOS_RECUPERADOS.getMensaje(),
                grupos
            ));
        } catch (Exception e) {
            log.error("Error al recuperar los grupos: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(
                false,
                CatalogoMensajes.ERROR_RECUPERAR_GRUPOS.getMensaje(),
                null,
                Collections.singletonList(e.getMessage())
            ));
        }
    }

    @GetMapping("/{grupoId}/estudiantes")
    public ResponseEntity<ApiResponse<List<EstudianteResponseDTO>>> listarEstudiantesPorGrupo(@PathVariable UUID grupoId) {
        try {
            List<EstudianteResponseDTO> estudiantes = listarEstudiantesPorGrupoInteractor.ejecutar(grupoId);
            if (estudiantes.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse<>(
                    true,
                    CatalogoMensajes.NO_ESTUDIANTES_GRUPO.getMensaje(),
                    estudiantes
                ));
            }
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                CatalogoMensajes.ESTUDIANTES_RECUPERADOS.getMensaje(),
                estudiantes
            ));
        } catch (Exception e) {
            log.error("Error al recuperar los estudiantes del grupo {}: {}", grupoId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(
                false,
                CatalogoMensajes.ERROR_RECUPERAR_ESTUDIANTES_GRUPO.getMensaje(),
                null,
                Collections.singletonList(e.getMessage())
            ));
        }
    }

    @GetMapping("/{grupoId}/sesiones")
    public ResponseEntity<ApiResponse<List<SesionResponseDTO>>> listarSesionesPorGrupo(@PathVariable UUID grupoId) {
        try {
            List<SesionResponseDTO> sesiones = listarSesionesPorGrupoInteractor.ejecutar(grupoId);
            if (sesiones.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse<>(
                    true,
                    CatalogoMensajes.NO_SESIONES_GRUPO.getMensaje(),
                    sesiones
                ));
            }
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                CatalogoMensajes.SESIONES_GRUPO_EXITO.getMensaje(),
                sesiones
            ));
        } catch (Exception e) {
            log.error("Error al recuperar las sesiones del grupo {}: {}", grupoId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(
                false,
                CatalogoMensajes.ERROR_SESIONES_GRUPO.getMensaje(),
                null,
                Collections.singletonList(e.getMessage())
            ));
        }
    }
}