package com.cosmink.resources;

import com.cosmink.models.groupDetails.GroupDetails;
import com.cosmink.models.userGroup.UserGroup;
import com.cosmink.models.userGroup.UserGroupDao;
import com.cosmink.models.userGroup.UserGroupFactory;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/groups")
public class GroupResource {

    @Inject
    private UserGroupDao userGroupDao;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN"})
    public Response createUserGroup(GroupDetails groupDetails){
        UserGroup userGroup = UserGroupFactory.createUserGroup(groupDetails);
        Boolean created = userGroupDao.create(userGroup);

        if (created){
            return Response.ok(userGroup, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(500).build();
        }
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN"})
    public Response getGate(@PathParam("id") long id){
        UserGroup userGroup = userGroupDao.findById(id);
        return Response.ok(userGroup, MediaType.APPLICATION_JSON).build();
    }
}
