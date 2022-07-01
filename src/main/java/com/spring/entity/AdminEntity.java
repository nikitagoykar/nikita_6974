package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Builder
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	
	@Column(nullable = false)
	@NotEmpty(message = "admin name can not be empty")
	@Size(min = 4,max =25,message = "name size is in between min 4 to max 25")
	private String adminName;
	
	@Column(nullable = false)
	@NotEmpty(message = "password can not be empty")
	@Size(min = 4, max = 25,message = "password has contains minimum 4 characters and maximum 25") 
    private String password;
	
	@OneToOne
	private HostelEntity hostel;
	
}
