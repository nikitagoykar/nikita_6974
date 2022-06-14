package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;
	
	@NotEmpty(message = "student name can not be empty")
	@Size(min = 4, max = 45, message = "name size is in between min 4 to max 45")
	@Column(nullable = false)
	private String studentName;
	
	@NotEmpty(message = "student email can not be empty")
	@Size(min = 4, max = 45, message = "email size is in between min 4 to max 45")
	@Column(nullable = false)
	private String  studentEmail;
	
	@NotEmpty(message = "student address can not be empty")
	@Size(min = 4, max = 45, message = "address size is in between min 4 to max 45")
	@Column(nullable = false)
	private String studentAddress;
	
	@NotEmpty(message = "student contact can not be empty")
	@Size(min = 4, max = 12, message = "contact size is in between min 4 to max 12")
	@Column(nullable = false)
	private String studentContact;
	
	@ManyToOne
	private WardenEntity warden;
	
	@ManyToOne
	private FloorEntity floor;
	
	@ManyToOne
	private RoomEntity room;
	
}
