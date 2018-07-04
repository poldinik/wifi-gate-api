package com.cosmink.models.gate;

import com.cosmink.models.BaseEntity;
import com.cosmink.models.gateType.GateType;
import com.cosmink.models.wifiLogin.WifiLogin;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement
@Entity
@NamedQueries({
        @NamedQuery(name = "gate.findById", query = "SELECT g FROM Gate g WHERE g.id = :id")

})
public class Gate extends BaseEntity{

    private String name;
    private String description;
    private Boolean isSync;
    @Embedded private WifiLogin wifiLogin;
    private Set<GateType> gateTypes;

    @ElementCollection(targetClass = GateType.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "gatetypes")
    //@XmlTransient //risolve problema che read di un un oggetto dà errore su enum, soluzione qual è? Errore era nel targetclass di element collection
    public Set<GateType> getGateTypes() {
        return gateTypes;
    }

    public void setGateTypes(Set<GateType> gateTypes) {
        this.gateTypes = gateTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSync() {
        return isSync;
    }

    public void setSync(Boolean sync) {
        isSync = sync;
    }


    public WifiLogin getWifiLogin() {
        return wifiLogin;
    }

    public void setWifiLogin(WifiLogin wifiLogin) {
        this.wifiLogin = wifiLogin;
    }
}
