package com.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WardenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wardenId;
	
	@NotEmpty(message = "warden name can not be empty")
	@Size(min = 4, max = 45, message = "name size is in between min 4 to max 45")
	@Column(nullable = false)
	private String wardenName;
	
	@NotEmpty(message = "warden Contact can not be empty")
	@Size(min = 4, max = 25, message = "contact size is in between min 4 to max 25")
	@Column(nullable = false)
	private String wardenContact;

	@ManyToOne
	private HostelEntity hostel;
	
	@OneToOne
	private FloorEntity floor ;
	
	@OneToMany(cascade = CascadeType.ALL)
	private  List<StudentEntity> student = new ArrayList<StudentEntity>();
}
