package com.spring.payload;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDto {

	private int studentId;
	private String studentName;
	private String  studentEmail;
	private String studentAddress;
	private String studentContact;
	
	private WardenDto warden;
	private FloorDto floor;
	private RoomDto room;
}

