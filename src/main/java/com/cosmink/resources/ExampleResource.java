package com.cosmink.resources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Example of Resource
 *
 * @author cosmink.com
 */

@Path("default")
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    public String getTest(){
        return "It works!";
    }

}
