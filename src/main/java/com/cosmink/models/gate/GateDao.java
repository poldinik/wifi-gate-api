package com.cosmink.models.gate;

import com.cosmink.models.CRUD;
import com.cosmink.models.Dao;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import java.util.List;

public class GateDao extends Dao implements CRUD<Gate> {
    @Override
    public Boolean create(Gate entity) throws EntityExistsException, IllegalStateException, IllegalArgumentException, TransactionRequiredException {
        entityManager.persist(entity);
        return true;
    }

    @Override
    public Gate read(long primaryKey) throws IllegalStateException, IllegalArgumentException {
        return null;
    }

    @Override
    public Boolean update(Gate entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException {
        entityManager.merge(entity);
        return true;
    }

    @Override
    public Boolean delete(long id) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException {
        Gate gate = findById(id);
        entityManager.remove(gate);
        return true;
    }

    public Gate findById(long id){

        //TODO: gestire la ricerca nulla
        List<Gate> gates = entityManager.createNamedQuery("gate.findById", Gate.class)
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList();
        return gates.get(0);
    }

    public List<Gate> getGates() throws Exception {
        Query query = entityManager.createQuery("SELECT g from Gate as g");
        return query.getResultList();
    }
}
