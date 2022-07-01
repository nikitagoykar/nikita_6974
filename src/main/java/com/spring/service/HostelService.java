package com.spring.service;

import java.util.List;

import com.spring.payload.HostelDto;

public interface HostelService {
	
	//create hostel
	public HostelDto createHostel(HostelDto hostelDto,int adminId);
	
	//get all hostel
	public List<HostelDto> getAllHostels();
	
	//get hostel by id
	public HostelDto getHostelById(int hostelId);
	
	//update hostel by id
	public HostelDto updateHostelById(HostelDto hostelDto,int hostelId);
	
	//delete hostel by id
	public void deleteHostelById(int hostelId);
	
	public List<HostelDto> getAllHostelsByAdminId(int adminId);
	

}
