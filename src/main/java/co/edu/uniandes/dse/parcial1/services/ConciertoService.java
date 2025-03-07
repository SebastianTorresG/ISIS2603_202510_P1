package co.edu.uniandes.dse.parcial1.services;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.ConciertoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConciertoService {

    @Autowired
    ConciertoRepository conciertoRepository;

    @Transactional
    public ConciertoEntity createConcierto(ConciertoEntity concierto) throws IllegalOperationException {
        log.info("Inicia proceso de creación del concierto");
        LocalDateTime hoy = LocalDateTime.now();
        if (hoy.isAfter(concierto.getFecha())) {
            throw new IllegalOperationException("EL concierto esta en el pasado");
        }
        if (concierto.getAforo() < 10) {
            throw new IllegalOperationException("El aforo del concierto es muy pequeño");
        }
        if (concierto.getPresupuesto() < 1000) {
            throw new IllegalOperationException("El presupuesto del concierto es muy pequeño");
        }
        return conciertoRepository.save(concierto);
    }

    @Transactional
    public List<ConciertoEntity> getConciertos() {
        log.info("Inicia proceso de consultar todos los conciertos");
        return conciertoRepository.findAll();
    }

    @Transactional
    public ConciertoEntity getConcierto(Long conciertoId) throws EntityNotFoundException {
        log.info("Inicia proceso de consultar el concierto con id = {0}", conciertoId);
        Optional<ConciertoEntity> conciertoEntity = conciertoRepository.findById(conciertoId);

        log.info("Termina proceso de consultar el concierto con id = {0}", conciertoId);
        return conciertoEntity.get();
    }

    @Transactional
    public ConciertoEntity updateConcierto(Long conciertoId, ConciertoEntity concierto) throws EntityNotFoundException {
        log.info("Inicia proceso de actualizar el concierto con id = {0}", conciertoId);
        Optional<ConciertoEntity> conciertoEntity = conciertoRepository.findById(conciertoId);
        if (conciertoEntity.isEmpty())
            throw new EntityNotFoundException("El concierto esta vacio");
        log.info("Termina proceso de actualizar el concierto con id = {0}", conciertoId);
        concierto.setId(conciertoId);
        return conciertoRepository.save(concierto);
    }

    @Transactional
    public void deleteconcierto(Long conciertoId) throws IllegalOperationException, EntityNotFoundException {
        log.info("Inicia proceso de borrar el concierto con id = {0}", conciertoId);
        Optional<ConciertoEntity> conciertoEntity = conciertoRepository.findById(conciertoId);
        if (conciertoEntity.isEmpty())
            throw new EntityNotFoundException("No se encontro concierto");
        conciertoRepository.deleteById(conciertoId);
        log.info("Termina proceso de borrar el concierto con id = {0}", conciertoId);
    }

}
