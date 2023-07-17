package com.project.cinemax.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    private String roleName;
    private String roleDescription;
    
    @ManyToMany(mappedBy = "roles")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Collection<User> users;
    
    
//    public void addUser(User user) {
//        this.users.add(user);
//        user.getRoles().add(this);
//    }
//  
//    public void removeUser(User user) {
//        this.users.remove(user);
//        user.getRoles().remove(this);
//    }
    
    public Role() {
		super();
	}
    
    public Role(String roleName, String roleDescription, Collection<User> users) {
		super();
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.users = users;
	}
    
    public Role(String roleName, String roleDescription) {
		super();
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}

    
	public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
    
    public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	
}
