package com.sebastian_daschner.coffee_shop.boundary;

import com.sebastian_daschner.coffee_shop.orders.entity.CoffeeType;
import com.sebastian_daschner.coffee_shop.orders.entity.Order;
import com.sebastian_daschner.coffee_shop.orders.entity.Origin;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.UUID;
import java.util.concurrent.locks.LockSupport;

public class OrderIT {

    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Before
    public void setUp() {
        entityManager = Persistence.createEntityManagerFactory("it").createEntityManager();
        transaction = entityManager.getTransaction();
    }

    @Test
    public void test() {
        transaction.begin();
        Origin colombia = new Origin("Colombia");
        entityManager.merge(colombia);

        Order entity = new Order(UUID.randomUUID(), CoffeeType.ESPRESSO, colombia);
        entityManager.merge(entity);

        transaction.commit();
        LockSupport.parkNanos(100_000_000_000L);
    }

}