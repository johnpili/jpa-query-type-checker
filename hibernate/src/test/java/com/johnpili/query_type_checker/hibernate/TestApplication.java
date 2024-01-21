package com.johnpili.query_type_checker.hibernate;

import com.johnpili.query_type_checker.QueryTypeChecker;
import com.johnpili.query_type_checker.QueryTypeCheckerImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestApplication {

    QueryTypeChecker queryTypeChecker = new QueryTypeCheckerImpl();

    @Test
    public void testIsSQLQuery() {
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit")) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createNativeQuery("SELECT * FROM material");
            Assertions.assertEquals(queryTypeChecker.getQueryType(query), QueryTypeChecker.QueryType.SQL);
        } catch (Exception exception) {
            Assertions.fail(exception);
        }
    }

    @Test
    public void testIsJPQLQuery() {
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit")) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createQuery("SELECT o FROM ProductionOrder o", ProductionOrder.class);
            Assertions.assertEquals(queryTypeChecker.getQueryType(query), QueryTypeChecker.QueryType.JPQL);
        } catch (Exception exception) {
            Assertions.fail(exception);
        }
    }
}
