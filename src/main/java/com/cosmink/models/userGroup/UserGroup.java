package com.cosmink.models.userGroup;

import com.cosmink.models.BaseEntity;
import com.cosmink.models.groupDetails.GroupDetails;
import com.cosmink.models.user.User;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@Entity
@NamedQueries({
        @NamedQuery(name = "UserGroup.findById", query = "SELECT ug FROM UserGroup ug WHERE ug.id = :id")

})
public class UserGroup extends BaseEntity{

    /*private List<user> users = new ArrayList<user>();

    //FIXME: correggere relazione many to many
    @ManyToMany(mappedBy = "userGroups")
    public List<user> getUsers() {
        return users;
    }

    public void setUsers(List<user> users) {
        this.users = users;
    }*/
    @Embedded private GroupDetails groupDetails;


    public GroupDetails getGroupDetails() {
        return groupDetails;
    }

    public void setGroupDetails(GroupDetails groupDetails) {
        this.groupDetails = groupDetails;
    }

    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "userGroups")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
        user.getUserGroups().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getUserGroups().remove(this);
    }
}
