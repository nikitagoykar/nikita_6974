package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.exception.ApiResponce;
import com.spring.payload.WardenDto;
import com.spring.service.WardenService;

@RestController
@RequestMapping("/api")
public class WardenController {

	@Autowired
	private WardenService wardenService;
	
	//add warden record by wardenId
	@PostMapping("/hostel/{hostelId}/warden")
	private ResponseEntity<WardenDto> createWarden(@PathVariable int hostelId,@RequestBody WardenDto wardenDto)
	{
		WardenDto createdWardenDto = this.wardenService.createWarden(wardenDto, hostelId);
		return new ResponseEntity<WardenDto>(createdWardenDto,HttpStatus.CREATED);
	}
	
	///fetch warden record by wardenId
	@GetMapping("/warden/{wardenId}")
	private ResponseEntity<WardenDto> getWardenById(@PathVariable int wardenId)
	{
		WardenDto getWarden = this.wardenService.getWardenById(wardenId);
		return new ResponseEntity<WardenDto>(getWarden,HttpStatus.OK);
	}
	//fetch all warden record
	@GetMapping("/warden")
	ResponseEntity<List<WardenDto>> createWarden()
	{
		List<WardenDto> recivedAllWardens = this.wardenService.getAllWardens();
		return new ResponseEntity<List<WardenDto>>(recivedAllWardens,HttpStatus.OK);
	}
	
	//fetech all wardens by hostel id
	@GetMapping("wardens/{hostelId}")
	ResponseEntity<List<WardenDto>> getwardenbyhostelId(@PathVariable int hostelId)
	{
		List<WardenDto> getHostel = this.wardenService.getWardenByHostelId(hostelId);
		return new ResponseEntity<List<WardenDto>>(getHostel,HttpStatus.OK);
	}
	
	//update warden record by wardenId
	@PutMapping("/warden/{wardenId}")
	ResponseEntity<WardenDto> updateWardenById(@RequestBody WardenDto wardenDto,@PathVariable int wardenId)
	{
		WardenDto updatedWarden = this.wardenService.updatedWardenById(wardenDto, wardenId);
		return new ResponseEntity<WardenDto>(updatedWarden,HttpStatus.OK);
	}
	
	//delete warden record by wardenId
	@DeleteMapping("/warden/{wardenId}")
	ResponseEntity<ApiResponce> deleteWarden(@PathVariable int wardenId)
	{
		this.wardenService.deleteWardenById(wardenId);
		
		ApiResponce responce = new ApiResponce();
		responce.setMessage("warden deleted suceesfully");
		responce.setSucess(true);
		
		return new ResponseEntity<ApiResponce>(responce,HttpStatus.OK);
	}
}
