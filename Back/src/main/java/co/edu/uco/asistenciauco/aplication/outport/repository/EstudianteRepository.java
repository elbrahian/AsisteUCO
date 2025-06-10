package co.edu.uco.asistenciauco.aplication.outport.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uco.asistenciauco.aplication.outport.entity.EstudianteEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity, UUID> {
}