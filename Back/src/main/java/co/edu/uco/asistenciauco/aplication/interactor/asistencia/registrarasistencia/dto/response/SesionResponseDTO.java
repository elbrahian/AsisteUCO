package co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public final class SesionResponseDTO {
    private final UUID id;
    private final LocalDateTime fecha;
    private final UUID grupoId;

    public SesionResponseDTO(UUID id, LocalDateTime fecha, UUID grupoId) {
        this.id = id;
        this.fecha = fecha;
        this.grupoId = grupoId;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public UUID getGrupoId() {
        return grupoId;
    }
}
