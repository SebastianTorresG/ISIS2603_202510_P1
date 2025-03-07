package co.edu.uniandes.dse.parcial1.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class ConciertoEntity extends BaseEntity {

    private String nombre;
    private String nombreArtista;
    private int aforo;
    private Long presupuesto;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @PodamExclude
    @ManyToOne
    private EstadioEntity estadio;

}
