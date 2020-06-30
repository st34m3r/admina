package org.cannonbank.core.Entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity

@Table(name = "agency")
public class Agency implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	
	private Integer agencyId;

	@NotBlank(message = "name is mandatory")
	private String name;
	@NotBlank(message = "adress is mandatory")
	private String address;
	@NotBlank(message = "country is mandatory")
	private String country;
	@NotBlank(message = "email is mandatory")
	@Email
	private String email;
	@NotBlank(message = "phone is mandatory")
	private String phone;
	@NotBlank(message = "fax is mandatory")
	private String fax;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agency")
	private Set<Banker> bankers = new HashSet<Banker>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agency")
	private Set<Location> locations = new HashSet<Location>(0);


	public Agency(String name, String address, String country, String email, String phone, String fax) {
		this.name = name;
		this.address = address;
		this.country = country;
		this.email = email;
		this.phone = phone;
		this.fax = fax;
	}





}
