package co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.response;

import java.util.UUID;

public final class GrupoResponseDTO {
    private final UUID id;
    private final String profesor;
    private final String materia;
    private final UUID profesorId;
    private final int cantidadEstudiantes;

    public GrupoResponseDTO(UUID id, String profesor, UUID profesorId ,String materia, int cantidadEstudiantes) {
        this.id = id;
        this.profesor = profesor;
        this.profesorId = profesorId;
        this.materia = materia;
        this.cantidadEstudiantes = cantidadEstudiantes;
    }


    public UUID getId() {
        return id;
    }

    public String getProfesor() {
        return profesor;
    }

    public String getMateria() {
        return materia;
    }

    public UUID getProfesorId() {
        return profesorId;
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }
}
