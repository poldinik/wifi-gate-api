package com.cosmink.models.authenticationToken;

import com.cosmink.models.authenticationTokenDetails.AuthenticationTokenDetails;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class AuthenticationToken {

    private String token;

    private AuthenticationTokenDetails authenticationTokenDetails;

    public AuthenticationTokenDetails getAuthenticationTokenDetails() {
        return authenticationTokenDetails;
    }

    @XmlTransient //evita di ritornare dettagli del token
    public void setAuthenticationTokenDetails(AuthenticationTokenDetails authenticationTokenDetails) {
        this.authenticationTokenDetails = authenticationTokenDetails;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
