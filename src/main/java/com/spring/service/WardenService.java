package com.spring.service;

import java.util.List;

import com.spring.payload.WardenDto;

public interface WardenService {
	public WardenDto createWarden(WardenDto wardenDto,int hostelId);
	
	public WardenDto getWardenById(int wardenId);
	
	public List<WardenDto> getAllWardens();
	
    public WardenDto updatedWardenById(WardenDto wardenDto,int wardenId);
    
    public void deleteWardenById(int wardenId);
}
