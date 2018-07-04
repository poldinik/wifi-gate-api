package com.cosmink.models.userGroup;


import com.cosmink.models.groupDetails.GroupDetails;

import java.util.Date;
import java.util.UUID;

public class UserGroupFactory {

    public static UserGroup createUserGroup(GroupDetails groupDetails){
        UserGroup userGroup = new UserGroup();
        userGroup.setUuid(UUID.randomUUID().toString());

        Date date = new Date();

        userGroup.setCreated(date);
        userGroup.setUpdate(date);
        userGroup.setGroupDetails(groupDetails);

        return userGroup;
    }


}
