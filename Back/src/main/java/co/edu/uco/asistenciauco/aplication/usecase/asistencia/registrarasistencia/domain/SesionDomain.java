package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain;

import java.util.UUID;

public final class SesionDomain {
	private UUID id;
	public SesionDomain(final UUID id) {
		setId(id);
	}
	public UUID getId() {
		return id;
	}
	private void setId(final UUID id) {
		this.id = id;
	}
}