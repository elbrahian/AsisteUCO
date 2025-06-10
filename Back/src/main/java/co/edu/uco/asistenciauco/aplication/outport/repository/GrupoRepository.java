package co.edu.uco.asistenciauco.aplication.outport.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uco.asistenciauco.aplication.outport.entity.GrupoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoEntity, UUID> {
    boolean existsByIdAndProfesorId(UUID grupoId, UUID profesorId);
}