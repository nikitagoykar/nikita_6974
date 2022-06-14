package com.spring.service;

import java.util.List;

import com.spring.payload.FloorDto;

public interface FloorService {

	public FloorDto createFloor(FloorDto floorDto,int wardenId);
	
	public FloorDto getFloorById(int floorId);
	
	public List<FloorDto> getAllFloors();
	
	public FloorDto updateFloorById(FloorDto floorDto,int floorId);
	
	public void deleteFloorById(int floorId);
}
