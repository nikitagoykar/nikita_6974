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
import com.spring.payload.RoomDto;
import com.spring.service.RoomService;

@RestController
@RequestMapping("/api")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@PostMapping("/floor/{floorId}/room")
	private ResponseEntity<RoomDto> createRoom(@PathVariable int floorId,@RequestBody RoomDto roomDto)
	{
		System.out.println("floorId is present"+floorId);
		RoomDto createdRoomDto = this.roomService.createRoom(roomDto, floorId);
		return new ResponseEntity<RoomDto>(createdRoomDto,HttpStatus.CREATED);
	}

	@GetMapping("/room/{roomId}")
	private ResponseEntity<RoomDto> getRoomById(@PathVariable int roomId)
	{
		RoomDto getRoom = this.roomService.getRoomById(roomId);
		return new ResponseEntity<RoomDto>(getRoom,HttpStatus.OK);
		
	}
	
	@GetMapping("/room")
	ResponseEntity<List<RoomDto>> createRoom()
	{
		List<RoomDto> recivedAllRooms = this.roomService.getAllRooms();
	return new ResponseEntity<List<RoomDto>>(recivedAllRooms,HttpStatus.OK);	
	}
	
	@PutMapping("/room/{roomId}")
	ResponseEntity<RoomDto> updateRoomById(@RequestBody RoomDto roomDto,@PathVariable int roomId)
	{
		RoomDto updateRoom = this.roomService.updateRoomById(roomDto, roomId);
		return new ResponseEntity<RoomDto>(updateRoom,HttpStatus.OK);
	}
	
	@DeleteMapping("/room/{roomId}")
	ResponseEntity<ApiResponce> deleteRoom(@PathVariable int roomId)
	{
		this.roomService.deleteRoomById(roomId);
		
		ApiResponce responce = new ApiResponce();
		responce.setMessage("room deleted sucessfully");
		responce.setSucess(true);
		
		return new ResponseEntity<ApiResponce>(responce,HttpStatus.OK);
	}
	@GetMapping("/room1/{floorId}")
	ResponseEntity  <List<RoomDto>> getAllRoomsByFloorId(@PathVariable int floorId)
	{
		List<RoomDto> recivedAllRooms = this.roomService.getAllRoomsByFloorId(floorId);
				
		return new ResponseEntity<List<RoomDto>>(recivedAllRooms,HttpStatus.OK);
	}
}
