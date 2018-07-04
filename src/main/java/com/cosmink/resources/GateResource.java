package com.cosmink.resources;

import com.cosmink.models.gate.Gate;
import com.cosmink.models.gate.GateDao;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/gates")
public class GateResource {

    @Inject
    private GateDao gateDao;

    //POST-create

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN"})
    public Response createGate(Gate gate){
        System.out.println(gate.getName());
        /*Set<gateType> gatetypes = new HashSet<>();
        gatetypes.add(gateType.LIGHT);
        gate.setGateTypes(gatetypes);*/
        Boolean created = gateDao.create(gate);
        if (created){
            return Response.ok(gate, MediaType.APPLICATION_JSON).status(201).build();
        }else {
            return Response.status(500).build();
        }
    }

    //GET READ

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN"})
    public Response getGate(@PathParam("id") long id){
        Gate gate = gateDao.findById(id);
        return Response.ok(gate, MediaType.APPLICATION_JSON).build();
    }

    //UPDATE

    @PUT
    @Transactional
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN"})
    public Response putGate(@PathParam("id") long id, Gate gate){
        gate.setId(id);
        Boolean updated =  gateDao.update(gate);

        if (updated){
            return Response.ok().build();
        } else {
            return Response.status(500).build();
        }

    }

    //DELETE

    @DELETE
    @Transactional
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN"})
    public Response deleteGate(@PathParam("id") long id){

        Boolean deleted = gateDao.delete(id);
        if (deleted){
            return Response.ok().build();
        } else {
            return Response.status(500).build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN"})
    public List<Gate> getUsers() throws Exception {
        List<Gate> gates = gateDao.getGates();
        return gates;
    }
}
