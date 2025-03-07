package co.edu.uniandes.dse.parcial1.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class EstadioEntity extends BaseEntity {

    private String nombre;
    private String nombreCiudad;
    private Long precioAlquiler;
    private int capacidad;

    @PodamExclude
    @OneToMany(mappedBy = "estadio", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ConciertoEntity> conciertos = new ArrayList<>();

}
