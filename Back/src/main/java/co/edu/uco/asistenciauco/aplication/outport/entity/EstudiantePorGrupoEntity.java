package co.edu.uco.asistenciauco.aplication.outport.entity;

import java.util.UUID;

import co.edu.uco.asistenciauco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.asistenciauco.crosscutting.helpers.UUIDHelper;
import jakarta.persistence.*;

@Entity
@Table(name = "EstudianteGrupo")
public class EstudiantePorGrupoEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "estudiante")
    private EstudianteEntity estudiante;

    @ManyToOne
    @JoinColumn(name = "grupo")
    private GrupoEntity grupo;

    @Column(name = "cancelado")
    private boolean cancelado;

    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }
    
    public EstudianteEntity getEstudiante() {
        return estudiante;
    }
    
    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = ObjectHelper.getDefault(estudiante, new EstudianteEntity());
    }
    
    public GrupoEntity getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoEntity grupo) {
        this.grupo = ObjectHelper.getDefault(grupo, new GrupoEntity());
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public EstudiantePorGrupoEntity() {
        setDefaultId();
        setDefaultEstudiante();
        setDefaultGrupo();
        setDefaultCancelado();
    }

    public EstudiantePorGrupoEntity(final UUID id, final EstudianteEntity estudiante, final GrupoEntity grupo, final boolean cancelado) {
        setId(id);
        setEstudiante(estudiante);
        setGrupo(grupo);
        setCancelado(cancelado);
    }

    private void setDefaultId() {
        setId(UUIDHelper.generate());
    }

    private void setDefaultEstudiante() {
        setEstudiante(new EstudianteEntity());
    }

    private void setDefaultGrupo() {
        setGrupo(new GrupoEntity());
    }

    private void setDefaultCancelado() {
        setCancelado(false);
    }
}
