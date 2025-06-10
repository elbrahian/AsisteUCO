package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public final class EstudiantePorGrupoDomain {
    private UUID id;
    private GrupoDomain grupo;
    private EstudianteDomain estudiante;
}