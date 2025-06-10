package co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.asistencia;

import co.edu.uco.asistenciauco.aplication.exception.AsistenciaValidationException;
import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel.CatalogoMensajes;
import co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.RegistrarAsistenciaInteractor;
import co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.request.RegistrarAsistenciaRequestDTO;

import java.util.Collections;

@Slf4j
@CrossOrigin(originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/asistencias")
public class AsistenciaController {
	private final RegistrarAsistenciaInteractor registrarAsistenciaInteractor;

	@PostMapping
	public ResponseEntity<ApiResponse<Void>> registrarAsistencia(@RequestBody RegistrarAsistenciaRequestDTO dto) {
		try {
			registrarAsistenciaInteractor.ejecutar(dto);
			return ResponseEntity.ok(new ApiResponse<>(
				true,
					CatalogoMensajes.ASISTENCIA_REGISTRADA.getMensaje(),
				null
			));

		} catch (AsistenciaValidationException e) {
			log.error("Error de validación al registrar asistencia: {}", e.getMessage());
			return ResponseEntity.badRequest().body(new ApiResponse<>(
				false,
				CatalogoMensajes.ERROR_VALIDACION_ASISTENCIA.getMensaje(),
				null,
				e.getErrores()
			));
		} catch (Exception e) {
			log.error("Error interno al registrar asistencia: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(
				false,
				CatalogoMensajes.ERROR_SERVIDOR_ASISTENCIA.getMensaje(),
				null,
				Collections.singletonList(e.getMessage())
			));
		}
	}
}