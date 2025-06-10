package co.edu.uco.asistenciauco.aplication.outport.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uco.asistenciauco.aplication.outport.entity.TipoIdentificacionEntity;

@Repository
public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacionEntity, UUID> {


}
