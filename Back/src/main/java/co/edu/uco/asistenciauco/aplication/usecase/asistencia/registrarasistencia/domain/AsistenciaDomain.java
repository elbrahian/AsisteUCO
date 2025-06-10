package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain;

import lombok.ToString;

@ToString
public final class AsistenciaDomain {
	private SesionDomain sesionDomain;
	private ProfesorDomain profesorDomain;
	private EstudiantePorGrupoDomain estudiantePorGrupoDomain;
	
	public AsistenciaDomain(final SesionDomain sesionDomain, final ProfesorDomain profesorDomain, final EstudiantePorGrupoDomain estudiantePorGrupoDomain) {
		setSesion(sesionDomain);
		setProfesor(profesorDomain);
		setEstudiantePorGrupoDomain(estudiantePorGrupoDomain);
	}
	
	public SesionDomain getSesion() {
		return sesionDomain;
	}
	private void setSesion(final SesionDomain sesionDomain) {
		this.sesionDomain = sesionDomain;
	}
	public ProfesorDomain getProfesor() {
		return profesorDomain;
	}
	private void setProfesor(final ProfesorDomain profesorDomain) {
		this.profesorDomain = profesorDomain;
	}
	public EstudiantePorGrupoDomain getEstudiantePorGrupoDomain() {
		return estudiantePorGrupoDomain;
	}
	public void setEstudiantePorGrupoDomain(EstudiantePorGrupoDomain estudiantePorGrupoDomain) {
		this.estudiantePorGrupoDomain = estudiantePorGrupoDomain;
	}
}