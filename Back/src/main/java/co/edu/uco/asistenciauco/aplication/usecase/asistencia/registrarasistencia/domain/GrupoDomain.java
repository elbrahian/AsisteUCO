package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@Setter
@AllArgsConstructor
public final class GrupoDomain {
    private UUID id;
}