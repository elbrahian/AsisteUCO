package co.edu.uco.asistenciauco.aplication.outport.entity;

import java.util.Date;
import java.util.UUID;

import co.edu.uco.asistenciauco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.asistenciauco.crosscutting.helpers.UUIDHelper;
import jakarta.persistence.*;

@Entity
@Table(name = "Sesion")
public class SesionEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "grupo")
    private GrupoEntity grupo;

    @Column(name = "fechaHora")
    private Date fechaHora;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    public GrupoEntity getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoEntity grupo) {
        this.grupo = ObjectHelper.getDefault(grupo, new GrupoEntity());
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
		this.fechaHora = ObjectHelper.getDefault(fechaHora, new Date());
	}

    public SesionEntity() {
        setDefaultId();
        setDefaultGrupo();
        setDefaultFechaHora();
    }

    public SesionEntity(UUID id, GrupoEntity grupo, Date fechaHora) {
        setId(id);
        setGrupo(grupo);
        setFechaHora(fechaHora);
    }

    private void setDefaultId() {
        setId(UUIDHelper.generate());
    }

    private void setDefaultGrupo() {
        setGrupo(new GrupoEntity());
    }

    private void setDefaultFechaHora() {
        setFechaHora(new Date());
    }
}
