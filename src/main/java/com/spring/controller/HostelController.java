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
import com.spring.payload.HostelDto;
import com.spring.service.HostelService;

@RestController
@RequestMapping("/api")
public class HostelController {

	@Autowired
	private HostelService hostelService;

	// add
	@PostMapping("/hostel")
	ResponseEntity<HostelDto> createHostel(@RequestBody HostelDto hostelDto) {
		HostelDto createdHostel = this.hostelService.createHostel(hostelDto);
		return new ResponseEntity<HostelDto>(createdHostel, HttpStatus.CREATED);
	}

	// getHostelById
	@GetMapping("/hostel/{hostelId}")
	ResponseEntity<HostelDto> createHostel(@PathVariable int hostelId) {
		HostelDto recivedHostel = this.hostelService.getHostelById(hostelId);
		return new ResponseEntity<HostelDto>(recivedHostel, HttpStatus.OK);
	}

	// getAllHostel
	@GetMapping("/hostel")
	ResponseEntity<List<HostelDto>> createHostel() {
		List<HostelDto> recivedAllHostels = this.hostelService.getAllHostels();
		return new ResponseEntity<List<HostelDto>>(recivedAllHostels, HttpStatus.OK);
	}

	// updateHostelById
	@PutMapping("/hostel/{hostelId}")
	ResponseEntity<HostelDto> updateHostelById(@RequestBody HostelDto hostelDto, @PathVariable int hostelId) {
		HostelDto updatedHostel = this.hostelService.updateHostelById(hostelDto, hostelId);
		return new ResponseEntity<HostelDto>(updatedHostel, HttpStatus.OK);
	}
	
	//delete hostel by id
	@DeleteMapping("/hostel/{hostelId}")
	ResponseEntity<ApiResponce>deleteHostel(@PathVariable int hostelId)
	{
		this.hostelService.deleteHostelById(hostelId);
		ApiResponce responce = new ApiResponce();
		responce.setMessage("hostel deleted succesfully");
		responce.setSucess(true);
		return new ResponseEntity<ApiResponce>(responce,HttpStatus.OK);
	}
	}

