package co.edu.uco.asistenciauco.aplication.interactor.asistencia.registrarasistencia.dto.request;

import java.util.UUID;

public final class RegistrarAsistenciaRequestDTO {
	private UUID sesion;
	private UUID profesor;
	private UUID estudianteGrupo;
	private boolean asistio;

	public RegistrarAsistenciaRequestDTO() {
		super();
	}

	public UUID getSesion() {
		return sesion;
	}

	public void setSesion(final UUID sesion) {
		this.sesion = sesion;
	}

	public UUID getProfesor() {
		return profesor;
	}

	public void setProfesor(final UUID profesor) {
		this.profesor = profesor;
	}

	public UUID getEstudianteGrupo() {
		return estudianteGrupo;
	}

	public void setEstudianteGrupo(UUID estudianteGrupo) {
		this.estudianteGrupo = estudianteGrupo;
	}

	public boolean isAsistio() {
		return asistio;
	}

	public void setAsistio(boolean asistio) {
		this.asistio = asistio;
	}
}