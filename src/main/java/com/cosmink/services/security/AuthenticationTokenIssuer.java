package com.cosmink.services.security;

import com.cosmink.models.authenticationTokenDetails.AuthenticationTokenDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Date;

@Dependent
public class AuthenticationTokenIssuer {

    @Inject
    private AuthenticationTokenSettings settings;

    /**
     * Issue a JWT token
     *
     * @param authenticationTokenDetails
     * @return
     */
    public String issueToken(AuthenticationTokenDetails authenticationTokenDetails) {

        return Jwts.builder()
                .setId(authenticationTokenDetails.getId())
                .setIssuer(settings.getIssuer())
                .setAudience(settings.getAudience())
                .setSubject(authenticationTokenDetails.getEmail())
                .setIssuedAt(Date.from(authenticationTokenDetails.getIssuedDate().toInstant()))
                .setExpiration(Date.from(authenticationTokenDetails.getExpirationDate().toInstant()))
                .claim(settings.getAuthoritiesClaimName(), authenticationTokenDetails.getAuthorities())
                .claim(settings.getRefreshCountClaimName(), authenticationTokenDetails.getRefreshCount())
                .claim(settings.getRefreshLimitClaimName(), authenticationTokenDetails.getRefreshLimit())
                .signWith(SignatureAlgorithm.HS256, settings.getSecret())
                .compact();
    }
}
