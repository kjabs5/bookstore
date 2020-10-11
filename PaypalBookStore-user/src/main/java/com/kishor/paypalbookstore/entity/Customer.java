package com.kishor.paypalbookstore.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private Integer id;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "city")
	private String city;

	@Column(name = "country")
	private String country;

	@Column(name = "phone")
	private String phone;

	@Column(name = "postcode")
	private String postcode;


	@Column(name = "register_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date register_date;
	
//	 @OneToOne
	 @OneToOne(fetch = FetchType.LAZY)
	    @MapsId
	   @JoinColumn(name = "user_id")
	    private Users user;

    @OneToMany(fetch=FetchType.LAZY, 
    		cascade=CascadeType.ALL,
    		mappedBy="customer")
    private List<Review> reviews;

	public Customer() {
		super();

	}

	public Customer(Integer id) {
		this.id = id;
		

	}

	

	public Customer(Integer id, String fullname, String address, String email, String city, String country,
			String phone, String postcode, Date register_date, Users user, List<Review> reviews) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.address = address;
		this.email = email;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.postcode = postcode;
		this.register_date = register_date;
		this.user = user;
		this.reviews = reviews;
	}

	public Integer getId() {
		return id;
	}













	public void setId(Integer id) {
		this.id = id;
	}













	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public Date getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}



	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}






	



	
	

}
