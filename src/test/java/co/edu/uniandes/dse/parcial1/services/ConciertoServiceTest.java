package co.edu.uniandes.dse.parcial1.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class ConciertoServiceTest {
    @Autowired
    private ConciertoService conciertoService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<ConciertoEntity> conciertoList = new ArrayList<>();

    /**
     * Configuración inicial de la prueba.
     */
    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from PrizeEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from BookEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from AuthorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ConciertoEntity conciertoEntity = factory.manufacturePojo(ConciertoEntity.class);
            entityManager.persist(conciertoEntity);
            conciertoList.add(conciertoEntity);
        }
    }

}
