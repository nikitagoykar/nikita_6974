package com.spring.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WardenDto {
	
	
	private int wardenId;
	private String wardenName;
	private String wardenContact;
	
	private HostelDto hostel;
	private FloorDto floor;
	private List<StudentDto> student;
}
