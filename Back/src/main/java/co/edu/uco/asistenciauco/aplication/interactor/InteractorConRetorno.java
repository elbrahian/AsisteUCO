package co.edu.uco.asistenciauco.aplication.interactor;

public interface InteractorConRetorno<I,O> {
	O ejecutar (I dto);
}
