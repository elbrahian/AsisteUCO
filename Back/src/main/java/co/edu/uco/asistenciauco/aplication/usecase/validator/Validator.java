package co.edu.uco.asistenciauco.aplication.usecase.validator;

public interface Validator <I, O>{

	O validate(I data);
}
