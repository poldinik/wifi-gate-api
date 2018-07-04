package com.cosmink.models.userGroup;

import com.cosmink.models.CRUD;
import com.cosmink.models.Dao;
import com.cosmink.models.membership.Membership;
import com.cosmink.models.user.User;
import org.hibernate.Hibernate;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import java.util.List;

public class UserGroupDao extends Dao implements CRUD<UserGroup> {
    @Override
    public Boolean create(UserGroup entity) throws EntityExistsException, IllegalStateException, IllegalArgumentException, TransactionRequiredException {
        entityManager.persist(entity);
        return true;
    }

    @Override
    public UserGroup read(long primaryKey) throws IllegalStateException, IllegalArgumentException {
        return null;
    }

    @Override
    public Boolean update(UserGroup entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException {
        return null;
    }

    @Override
    public Boolean delete(long id) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException {
        return null;
    }


    public Boolean createMembership(Membership membership){
        //TODO: gestire la ricerca nulla
        List<UserGroup> userGroups = entityManager.createNamedQuery("userGroup.findById", UserGroup.class)
                .setParameter("id", membership.getGroupId())
                .setMaxResults(1)
                .getResultList();
        UserGroup userGroup = userGroups.get(0);

        List<User> users = entityManager.createNamedQuery("user.findById", User.class)
                .setParameter("id", membership.getUserId())
                .setMaxResults(1)
                .getResultList();
        User user = users.get(0);

        userGroup.addUser(user);


        return true;
    }

    public UserGroup findById(long id){

        //TODO: gestire la ricerca nulla
        List<UserGroup> userGroups = entityManager.createNamedQuery("userGroup.findById", UserGroup.class)
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList();
        UserGroup userGroup = userGroups.get(0);
        Hibernate.initialize(userGroup.getUsers());
        return userGroup;
    }

    public List<UserGroup> getUserGroups() throws Exception {
        Query query = entityManager.createQuery("SELECT ug from UserGroup as ug");
        return query.getResultList();
    }
}
