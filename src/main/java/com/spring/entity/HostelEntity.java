package com.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class HostelEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hostelId;
	
	@Column(nullable = false)
	@NotEmpty(message = "hostel name can not be empty")
	@Size(min = 4,max =25,message = "name size is in between min 4 to max 25")
	private String hostelName;

	@OneToOne
	private AdminEntity admin;

	@OneToMany(cascade = CascadeType.ALL)
	private List<WardenEntity>  warden = new ArrayList<WardenEntity>();
}
