package com.spring.payload;

import com.spring.entity.AdminEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@ToString
public class HostelDto {


	private int hostelId;
	
	
	private String hostelName;
	private AdminDto admin;
	
}


