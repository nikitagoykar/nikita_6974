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
import com.spring.payload.FloorDto;
import com.spring.service.FloorService;

@RestController
@RequestMapping("/api")
public class FloorController {

	@Autowired
	private FloorService floorService;
        
	//Add floor
	@PostMapping("/warden/{wardenId}/floor")
	private ResponseEntity<FloorDto> createFloor(@PathVariable int wardenId, @RequestBody FloorDto floorDto) {
		FloorDto createFloorDto = this.floorService.createFloor(floorDto, wardenId);
		return new ResponseEntity<FloorDto>(createFloorDto, HttpStatus.CREATED);
	}

	//Fetch floor record by floorId
	@GetMapping("floor/{floorId}")
	private ResponseEntity<FloorDto> getFloorById(@PathVariable int floorId) {
		FloorDto getFloor = this.floorService.getFloorById(floorId);
		return new ResponseEntity<FloorDto>(getFloor, HttpStatus.OK);
	}

	//Fetch floor record by floorId
	@GetMapping("/floor")
	ResponseEntity<List<FloorDto>> createFloor() {
		List<FloorDto> recivedAllFloors = this.floorService.getAllFloors();
		return new ResponseEntity<List<FloorDto>>(recivedAllFloors, HttpStatus.OK);
	}
	
	//fetch floor by wardenId
	@GetMapping("/floors/{wardenId}")
	ResponseEntity<List<FloorDto>> getAllFloorsByWardenId(@PathVariable int wardenId)
	{
		List<FloorDto> getwarden = this.floorService.getAllFloorsByWardenId(wardenId);
		return new ResponseEntity<List<FloorDto>>(getwarden,HttpStatus.OK);
	}
	
	
	//update floor rcord by floorId
	@PutMapping("/floor/{floorId}")
	ResponseEntity<FloorDto> updateFloorById(@RequestBody FloorDto floorDto,@PathVariable int floorId)
	{
		FloorDto updateFloor = this.floorService.updateFloorById(floorDto, floorId);
		return new ResponseEntity<FloorDto>(updateFloor,HttpStatus.OK);
	}
	
	//delete floor Rcord by floorId
	@DeleteMapping("/floor/{floorId}")
	ResponseEntity<ApiResponce> deleteFloor(@PathVariable int floorId)
	{
		this.floorService.deleteFloorById(floorId);
		
		ApiResponce responce = new ApiResponce();
		responce.setMessage("floor deleted suceesfully");
		responce.setSucess(true);
		
		return new ResponseEntity<ApiResponce>(responce,HttpStatus.OK);
	}
		
	
}
