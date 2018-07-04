package com.cosmink.resources;

import com.cosmink.models.user.User;
import com.cosmink.models.user.UserDao;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;


@Path("/users")
@RequestScoped
public class UserResource {

    @Inject
    private UserDao userDao;

    @Context
    private UriInfo uriInfo;

    @Context
    private SecurityContext securityContext;

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN"})
    //@PermitAll
    public List<User> getUsers() throws Exception {
        List<User> users = userDao.getUsers();
        return users;
    }


}
