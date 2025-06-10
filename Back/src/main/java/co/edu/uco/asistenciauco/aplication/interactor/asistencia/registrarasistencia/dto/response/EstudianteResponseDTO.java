package co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response;

import java.util.UUID;

public final class EstudianteResponseDTO {
    private final UUID id;
    private final String numeroIdentificacion;
    private final String nombresCompletos;

    public EstudianteResponseDTO(UUID id, String numeroIdentificacion, String nombresCompletos) {
        this.id = id;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombresCompletos = nombresCompletos;
    }

    // getters
    public UUID getId() { return id; }
    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public String getNombresCompletos() { return nombresCompletos; }
}
