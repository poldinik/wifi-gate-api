package com.cosmink.models.grant;

import com.cosmink.models.CRUD;
import com.cosmink.models.Dao;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

public class GrantDao extends Dao implements CRUD<Grant>{
    @Override
    public Boolean create(Grant entity) throws EntityExistsException, IllegalStateException, IllegalArgumentException, TransactionRequiredException {
        entityManager.persist(entity);
        return true;
    }

    @Override
    public Grant read(long primaryKey) throws IllegalStateException, IllegalArgumentException {
        return null;
    }

    @Override
    public Boolean update(Grant entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException {
        return null;
    }

    @Override
    public Boolean delete(long id) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException {
        return null;
    }
}
