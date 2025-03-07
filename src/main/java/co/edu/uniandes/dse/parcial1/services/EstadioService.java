package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;

import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.EstadioRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EstadioService {

    @Autowired
    EstadioRepository estadioRepository;

    @Transactional
    public EstadioEntity createEstadio(EstadioEntity estadio) throws IllegalOperationException {
        log.info("Inicia proceso de creación del estadio");
        if (estadio.getNombreCiudad().length() < 3) {
            throw new IllegalOperationException("El nomnbre de la ciudad es muy corto");
        }
        if (estadio.getCapacidad() < 1000) {
            throw new IllegalOperationException("La capacidad del estadio es muy pequeña");
        }
        if (estadio.getPrecioAlquiler() < 100000) {
            throw new IllegalOperationException("El precio del alquiler es muy pequeño");
        }
        return estadioRepository.save(estadio);
    }

}
