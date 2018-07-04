package com.cosmink.models.grantDetails;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class GrantDetails {

    private long userId;
    private long gateId;
    private long alsoOpen;
    private Date startDate;
    private Date expiryDate;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getGateId() {
        return gateId;
    }

    public void setGateId(long gateId) {
        this.gateId = gateId;
    }

    public long getAlsoOpen() {
        return alsoOpen;
    }

    public void setAlsoOpen(long alsoOpen) {
        this.alsoOpen = alsoOpen;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
