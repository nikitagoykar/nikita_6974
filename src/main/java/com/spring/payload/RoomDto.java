package com.spring.payload;

import java.util.List;

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
public class RoomDto {

	private int roomId;
	private String roomType;
	
	private FloorDto floor;
	private List<StudentDto> student;

}
