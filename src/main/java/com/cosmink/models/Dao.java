package com.cosmink.models;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;


@Stateless
public abstract class Dao {

    @PersistenceContext(unitName = "persistence-unit-1", type = PersistenceContextType.EXTENDED)
    protected EntityManager entityManager;
}
