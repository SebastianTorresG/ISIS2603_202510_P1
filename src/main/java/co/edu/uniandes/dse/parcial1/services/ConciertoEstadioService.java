package co.edu.uniandes.dse.parcial1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;

import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.ConciertoRepository;
import co.edu.uniandes.dse.parcial1.repositories.EstadioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConciertoEstadioService {

    @Autowired
    private ConciertoRepository conciertoRepository;

    @Autowired
    private EstadioRepository estadioRepository;

    @Transactional
    public EstadioEntity addEstadio(ConciertoEntity concierto, EstadioEntity estadio, long conciertoId, long estadioId)
            throws IllegalOperationException {
        log.info("Inicia proceso de asociarle un estadio al concierto  = {0}", concierto);
        if (estadio.getCapacidad() > concierto.getAforo())
            throw new IllegalOperationException("La capacidad del concierto supera la del estadio");

        if (estadio.getPrecioAlquiler() < concierto.getPresupuesto())
            throw new IllegalOperationException("El precio del alquiler supera el presupuesto del concierto");

        if (estadio.getCapacidad() > concierto.getAforo())
            throw new IllegalOperationException("La capacidad del concierto supera la del estadio");

        Optional<ConciertoEntity> conciertoEntity = conciertoRepository.findById(conciertoId);

        Optional<EstadioEntity> estadioEntity = estadioRepository.findById(estadioId);

        estadioEntity.get().getConciertos().add(conciertoEntity.get());
        log.info("Termina proceso de asociarle un autor al libro con id = {0}", conciertoId);
        return estadioEntity.get();
    }

}
