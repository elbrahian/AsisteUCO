package co.edu.uco.asistenciauco.aplication.interactor.usecase;

public interface UseCaseConRetorno <D,O>{
	O ejecutar(D dominio);
}
