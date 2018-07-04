package com.cosmink.models.user;

import java.util.Date;
import java.util.UUID;

public class UserFactory {

    public static User createUser(){
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());

        Date date = new Date();

        user.setCreated(date);
        user.setUpdate(date);

        return user;
    }
}
