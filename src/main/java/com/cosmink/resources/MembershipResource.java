package com.cosmink.resources;

import com.cosmink.models.membership.Membership;
import com.cosmink.models.userGroup.UserGroupDao;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/memberships")
public class MembershipResource {

    @Inject
    private UserGroupDao userGroupDao;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN"})
    public Response addUserToGroup(Membership membership){
        Boolean added = userGroupDao.createMembership(membership);
        return Response.ok().build();
    }

}
