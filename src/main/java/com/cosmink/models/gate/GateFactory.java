package com.cosmink.models.gate;

import java.util.Date;
import java.util.UUID;

public class GateFactory {

    public static Gate createGate(){
        Gate gate = new Gate();
        gate.setUuid(UUID.randomUUID().toString());

        Date date = new Date();

        gate.setCreated(date);
        gate.setUpdate(date);

        return gate;
    }
}
