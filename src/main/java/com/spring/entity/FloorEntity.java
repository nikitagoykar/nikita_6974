package com.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FloorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int floorId;
	
	@NotEmpty(message = "floor name can not be empty")
	@Size(min = 4, max = 45, message = "name size is in between min 4 to max 45")
	@Column(nullable = false)
	private String floorName;
	
	@OneToOne
	private WardenEntity warden;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<RoomEntity> room = new ArrayList<RoomEntity>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<StudentEntity> student = new ArrayList<StudentEntity>();

}
