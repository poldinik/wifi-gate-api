package com.cosmink.resources;

import com.cosmink.models.authority.Authority;
import com.cosmink.models.user.User;
import com.cosmink.models.user.UserDao;
import com.cosmink.models.user.UserFactory;
import com.cosmink.models.userCredentials.UserCredentials;
import com.cosmink.models.userGroup.UserGroupDao;
import com.cosmink.services.security.PasswordEncoder;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

@Path("/register")
@RequestScoped
public class RegisterResource {

    @Inject
    private UserDao userDao;

    @Inject
    private UserGroupDao userGroupDao;

    //TODO: POST APIs per registrazione

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response register(UserCredentials userCredentials){

        User user = UserFactory.createUser();


        userCredentials.setPassword(PasswordEncoder.hashPassword(userCredentials.getPassword()));
        user.setUserCredentials(userCredentials);
        //TODO: implementare sistema di conferma con mail
        //simula registrazione di un utente ADMIN
        Set<Authority> authorities = new HashSet<>();
        authorities.add(Authority.ADMIN);
        //authorities.add(authority.USER);
        user.setAuthorities(authorities);


        Boolean created = userDao.create(user);
        System.out.println(user.getAuthorities().toString());


        if (created){
            return Response.ok(user, MediaType.APPLICATION_JSON).status(201).build();
        }else {
            return Response.status(500).build();
        }

    }

}
