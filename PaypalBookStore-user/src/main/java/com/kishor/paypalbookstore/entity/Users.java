package com.kishor.paypalbookstore.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
	private Integer id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	
	@Column(name="password_hint")
	private String password_hint;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@ManyToMany(fetch=FetchType.EAGER, 
    		cascade=CascadeType.MERGE)
	@JoinTable(name="roles_users",
	           joinColumns=@JoinColumn(name="user_id"),
	           inverseJoinColumns=@JoinColumn(name="role_id")
	)
    private Set<Roles> roles=new HashSet<>();
	
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
    private Customer customer;
	
	public Users() {
		super();
	}


	

	

	public Users(Integer id, String username, String password, String password_hint, boolean enabled, Set<Roles> roles,
			Customer customer) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.password_hint = password_hint;
		this.enabled = enabled;
		this.roles = roles;
		this.customer = customer;
	}






	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getPassword_hint() {
		return password_hint;
	}

	public void setPassword_hint(String password_hint) {
		this.password_hint = password_hint;
	}

//	public List<Roles> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(List<Roles> roles) {
//		this.roles = roles;
//	}
	

	public boolean isEnabled() {
		return enabled;
	}

	public Set<Roles> getRoles() {
		return roles;
	}



	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}






	public Customer getCustomer() {
		return customer;
	}






	public void setCustomer(Customer customer) {
		this.customer = customer;
	}






	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", password_hint="
				+ password_hint + ", enabled=" + enabled + ", roles=" + roles + ", customer=" + customer + "]";
	}



	

 

	


	
	
	
	
	

}
