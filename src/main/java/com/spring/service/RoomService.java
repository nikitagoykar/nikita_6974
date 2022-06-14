package com.spring.service;

import java.util.List;

import com.spring.payload.RoomDto;

public interface RoomService {

	public RoomDto createRoom(RoomDto roomDto,int floorId);
	
	public RoomDto getRoomById(int roomId);
	
	public List<RoomDto> getAllRooms();
	
	public RoomDto updateRoomById(RoomDto roomDto,int roomId);
	
	public  void deleteRoomById(int roomId);
	
	public List<RoomDto> getAllRoomsByFloorId(int floorId);
}
