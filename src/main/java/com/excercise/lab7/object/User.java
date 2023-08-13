package com.excercise.lab7.object;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") // Rename the table to "users"
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY) // Specify the ID generation strategy
//	private Long id;
	@Id
	private final String username;
	private final String password;
	private final String fullname;
	private final String street;
	private final String city;
	private final String state;
	private final String zip;
	private final String phoneNumber;
	private boolean enabled = true;
//    @ElementCollection(fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "user", orphanRemoval = true)
	private List<Authority> authorities = Arrays.asList(new Authority(this, "ROLE_USER"));

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public User() {
		this.username = "";
		this.password = "";
		this.fullname = "";
		this.street = "";
		this.city = "";
		// TODO Auto-generated constructor stub
		this.state = "";
		this.zip = "";
		this.phoneNumber = "";
	}

//	public User(Long id, String username, String password, String fullname, String street, String city, String state,
//			String zip, String phoneNumber) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.fullname = fullname;
//		this.street = street;
//		this.city = city;
//		this.state = state;
//		this.zip = zip;
//		this.phoneNumber = phoneNumber;
//	}

	public User(String username, String password, String fullname, String street, String city, String state, String zip,
			String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
	}

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFullname() {
		return fullname;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", fullname=" + fullname + ", street=" + street
				+ ", city=" + city + ", state=" + state + ", zip=" + zip + ", phoneNumber=" + phoneNumber + ", enabled="
				+ enabled + ", authorities=" + authorities + "]";
	}

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", username=" + username + ", password=" + password + ", fullname=" + fullname
//				+ ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + ", phoneNumber="
//				+ phoneNumber + "]";
//	}
	

}
