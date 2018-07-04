package com.cosmink.models.grant;

import com.cosmink.models.BaseEntity;
import com.cosmink.models.grantDetails.GrantDetails;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Grant extends BaseEntity{

    @Embedded private GrantDetails grantDetails;
    private String token;

    public GrantDetails getGrantDetails() {
        return grantDetails;
    }

    public void setGrantDetails(GrantDetails grantDetails) {
        this.grantDetails = grantDetails;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
