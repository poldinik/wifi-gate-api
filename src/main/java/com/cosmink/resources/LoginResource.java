package com.cosmink.resources;

import com.cosmink.models.authenticationToken.AuthenticationToken;
import com.cosmink.models.user.User;
import com.cosmink.models.userCredentials.UserCredentials;
import com.cosmink.services.security.AuthenticationTokenService;
import com.cosmink.services.security.UsernamePasswordValidator;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/login")
public class LoginResource {

    @Context
    private SecurityContext securityContext;

    @Inject
    private UsernamePasswordValidator usernamePasswordValidator;

    @Inject
    private AuthenticationTokenService authenticationTokenService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response authenticate(UserCredentials userCredentials){
        //TODO: implementare autenticazione

        User user = usernamePasswordValidator.validateCredentials(userCredentials.getEmail(), userCredentials.getPassword());

        String token = authenticationTokenService.issueToken(user.getUserCredentials().getEmail(), user.getAuthorities());

        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setToken(token);
        return Response.ok(authenticationToken, MediaType.APPLICATION_JSON).build();

    }

}
