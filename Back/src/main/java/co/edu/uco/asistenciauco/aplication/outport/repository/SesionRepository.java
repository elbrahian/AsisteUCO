package co.edu.uco.asistenciauco.aplication.outport.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uco.asistenciauco.aplication.outport.entity.SesionEntity;

@Repository
public interface SesionRepository extends JpaRepository<SesionEntity, UUID> {
    List<SesionEntity> findByGrupoId(UUID grupoId);
}