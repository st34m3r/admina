package org.cannonbank.core.Entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.cannonbank.core.Security.models.User;
import org.springframework.format.annotation.DateTimeFormat;


@Getter
@Setter

@NoArgsConstructor
@Entity

@Table(name = "banker")
public class Banker extends User implements java.io.Serializable  {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agency_id", nullable = false)
	private Agency agency;


	@NotBlank
	private String fname;
	@NotBlank
	private String lname;
	@NotBlank
	private String address;
	@NotBlank
	private String phone;

	public Banker(String username,
				  String email,
				  String password,
				  Agency agency,
				  String fname,
				  String lname,
				  String address,
				  String phone,
				  Date birthDate,
				  Date joiningDate) {

		super(username, email, password);

		this.agency = agency;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.phone = phone;
		this.birthDate = birthDate;
		this.joiningDate = joiningDate;


	}
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date joiningDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "banker")
	private Set<Client> clients = new HashSet<Client>(0);



}
