package com.cosmink.filters;

import com.cosmink.models.authenticatedUserDetails.AuthenticatedUserDetails;
import com.cosmink.models.authenticationTokenDetails.AuthenticationTokenDetails;
import com.cosmink.models.user.User;
import com.cosmink.models.user.UserDao;
import com.cosmink.services.security.AuthenticationTokenService;
import com.cosmink.services.security.TokenBasedSecurityContext;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Dependent
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    private UserDao userDao;

    @Inject
    private AuthenticationTokenService authenticationTokenService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String authenticationToken = authorizationHeader.substring(7);
            System.out.println("token preso");
            System.out.println(authenticationToken);
            handleTokenBasedAuthentication(authenticationToken, requestContext);
            return;
        }

        // Other authentication schemes (such as Basic) could be supported
    }

    private void handleTokenBasedAuthentication(String authenticationToken, ContainerRequestContext requestContext) {

        //FIXME: quando la getUsers() è annotata come @RolesAllowed({"ADMIN"}) ritorna errore perché non trova bene l'user
        //FIXME: infatti dà NullPointerException

        AuthenticationTokenDetails authenticationTokenDetails = authenticationTokenService.parseToken(authenticationToken);
        System.out.println(authenticationTokenDetails.getId());
        System.out.println("mail");
        System.out.println(authenticationTokenDetails.getEmail().toString());
        User user = userDao.findByUsernameOrEmail(authenticationTokenDetails.getEmail());
        if (user == null){
            System.out.println("non ho trovato l'user");
        }

        System.out.println(user.getUsername());

        //FIXME: non trova comunque l'user, probabilmente ci sono ancora problemi con la chiave di ricerca che voglio sia email e non username
        AuthenticatedUserDetails authenticatedUserDetails = new AuthenticatedUserDetails(user.getUserCredentials().getEmail(), user.getAuthorities());

        boolean isSecure = requestContext.getSecurityContext().isSecure();
        System.out.println("è sicuro:");
        System.out.println(isSecure);
        SecurityContext securityContext = new TokenBasedSecurityContext(authenticatedUserDetails, authenticationTokenDetails, isSecure);
        requestContext.setSecurityContext(securityContext);
    }
}
