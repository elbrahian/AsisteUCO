package co.edu.uco.asistenciauco.aplication.outport.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uco.asistenciauco.aplication.outport.entity.EstudiantePorGrupoEntity;

@Repository
public interface EstudianteGrupoRepository extends JpaRepository<EstudiantePorGrupoEntity, UUID> {
    boolean existsByEstudianteIdAndGrupoId(UUID estudianteId, UUID grupoId);
    Optional<EstudiantePorGrupoEntity> findByEstudianteIdAndGrupoId(UUID estudianteId, UUID grupoId);
    List<EstudiantePorGrupoEntity> findByGrupoId(UUID grupoId);
    boolean existsByEstudiante_Id(UUID estudianteId);
}