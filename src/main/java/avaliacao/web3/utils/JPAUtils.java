package avaliacao.web3.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtils {

    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("estudantes-web3");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

}
