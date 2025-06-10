package co.edu.uco.asistenciauco.aplication.outport.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uco.asistenciauco.aplication.outport.entity.ProfesorEntity;

public interface ProfesorRepository extends JpaRepository<ProfesorEntity, UUID> {
}
