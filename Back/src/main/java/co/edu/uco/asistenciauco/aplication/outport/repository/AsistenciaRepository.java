package co.edu.uco.asistenciauco.aplication.outport.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uco.asistenciauco.aplication.outport.entity.AsistenciaEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends JpaRepository<AsistenciaEntity, UUID> {
    boolean existsBySesion_id(UUID id);
    boolean existsBySesion_idAndEstudianteGrupo_id(UUID sesionId, UUID estudianteGrupoId);
}