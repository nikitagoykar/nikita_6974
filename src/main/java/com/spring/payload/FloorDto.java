package com.spring.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FloorDto {

	private int floorId;
	private String floorName;
	
	private WardenDto warden;
	
	private List<RoomDto> room;
	private List<StudentDto> student;
}
