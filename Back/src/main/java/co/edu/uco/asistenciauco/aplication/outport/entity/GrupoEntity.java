package co.edu.uco.asistenciauco.aplication.outport.entity;

import java.util.UUID;

import co.edu.uco.asistenciauco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.asistenciauco.crosscutting.helpers.UUIDHelper;
import jakarta.persistence.*;

@Entity
@Table(name = "Grupo")
public class GrupoEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "profesor")
    private ProfesorEntity profesor;

    @ManyToOne
    @JoinColumn(name = "materia")
    private MateriaEntity materia;

    @Column(name = "cantidadEstudiantes")
    private int cantidadEstudiantes;

    @Column(name = "estado")
    private boolean estado;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    public ProfesorEntity getProfesor() {
        return profesor;
    }

    public void setProfesor(ProfesorEntity profesor) {
        this.profesor = ObjectHelper.getDefault(profesor, new ProfesorEntity());
    }

    public MateriaEntity getMateria() {
        return materia;
    }

    public void setMateria(MateriaEntity materia) {
        this.materia = ObjectHelper.getDefault(materia, new MateriaEntity());
    }
    
    public boolean isEstado() {
        return estado;
    }
    
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    public void setCantidadEstudiantes(int cantidadEstudiantes) {
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    public GrupoEntity() {
        setDefaultId();
        setDefaultProfesor();
        setDefaultMateria();
        setDefaultCantidadEstudiantes();
        setDefaultEstado();
    }
    
    public GrupoEntity(final UUID id) {
        setId(id);
        setDefaultProfesor();
        setDefaultMateria();
        setDefaultCantidadEstudiantes();
        setDefaultEstado();
    }

    public GrupoEntity(UUID id, ProfesorEntity profesor, MateriaEntity materia, int cantidadEstudiantes) {
        setId(id);
        setProfesor(profesor);
        setMateria(materia);
        setCantidadEstudiantes(cantidadEstudiantes);
        setDefaultEstado();
    }

    private void setDefaultId() {
        setId(UUIDHelper.generate());
    }

    private void setDefaultProfesor() {
        setProfesor(new ProfesorEntity());
    }

    private void setDefaultMateria() {
        setMateria(new MateriaEntity());
    }

    private void setDefaultCantidadEstudiantes() {
        setCantidadEstudiantes(0);
    }
    
    private void setDefaultEstado() {
        setEstado(true);
    }
}
