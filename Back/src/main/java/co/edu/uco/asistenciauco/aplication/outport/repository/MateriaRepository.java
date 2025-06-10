package co.edu.uco.asistenciauco.aplication.outport.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uco.asistenciauco.aplication.outport.entity.MateriaEntity;

public interface MateriaRepository extends JpaRepository<MateriaEntity, UUID> {
	// Este repositorio se encarga de la persistencia de los datos de las materias
	// y su relación con la asistencia.
	// Se pueden agregar métodos personalizados si es necesario.

}
