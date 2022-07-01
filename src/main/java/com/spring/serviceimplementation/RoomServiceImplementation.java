package com.spring.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.FloorEntity;
import com.spring.entity.RoomEntity;
import com.spring.exception.ResourceNotFoundException;
import com.spring.payload.FloorDto;
import com.spring.payload.RoomDto;
import com.spring.repository.FloorRepository;
import com.spring.repository.RoomRepository;
import com.spring.service.RoomService;

@Service
public class RoomServiceImplementation implements RoomService {

	@Autowired
	private FloorRepository floorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public RoomDto createRoom(RoomDto roomDto, int floorId) {
		// TODO Auto-generated method stub
	
		FloorEntity floorEntity = this.floorRepository.findById(floorId)
				.orElseThrow(() -> new ResourceNotFoundException("Floor", "FloorId", floorId));

		System.out.println("Floor Id  ------"+floorEntity.getFloorId());
		System.out.println("Floor name-------"+floorEntity.getFloorName());
		System.out.println("List of Rooms----"+floorEntity.getRoom());
		System.out.println("Warden Id-----"+floorEntity.getWarden());
		
		roomDto.setFloor(this.modelMapper.map(floorEntity, FloorDto.class));      
		RoomEntity saveRoom= this.roomRepository.save(this.modelMapper.map(roomDto, RoomEntity.class));
		return this.modelMapper.map(saveRoom,RoomDto.class);
	}

	@Override
	public RoomDto getRoomById(int roomId) {
		// TODO Auto-generated method stub
		RoomEntity room = this.roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room", "RoomId", roomId));
		return this.modelMapper.map(room, RoomDto.class);
	}

	@Override
	public List<RoomDto> getAllRooms() {
		// TODO Auto-generated method stub
		List<RoomEntity> roomList= this.roomRepository.findAll();
		List<RoomDto> getAllRooms = roomList.stream().map(room -> this.modelMapper.map(room,RoomDto.class))
				.collect(Collectors.toList());
		return getAllRooms;
	}

	@Override
	public RoomDto updateRoomById(RoomDto roomDto, int roomId) {
		// TODO Auto-generated method stub
		RoomEntity roomEntity = this.roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room", "RoomId", roomId));
//		RoomEntity updatedRoomEntity = this.roomRepository.save(this.roomDtoToRoomEntity(roomDto));
//		return this.roomEntityToRoomDto(updatedRoomEntity);
		return this.modelMapper.map(roomEntity, RoomDto.class);
	}

	@Override
	public void deleteRoomById(int roomId) {
		// TODO Auto-generated method stub
		RoomEntity roomEntity = this.roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room", "RoomId", roomId));
		this.roomRepository.delete(roomEntity);
	}


	@Override
	public List<RoomDto> getAllRoomsByFloorId(int floorId)
	{
		// TODO Auto-generated method stub
		FloorEntity floor = this.floorRepository.findById(floorId)
				.orElseThrow(() -> new ResourceNotFoundException("Floor", "FloorId", floorId));
		List<RoomEntity> rooms = this.roomRepository.findByFloor(floor);

		List<RoomDto> roomDtoList = rooms.stream().map(room ->this.modelMapper.map(room,RoomDto.class)).collect(Collectors.toList());
           return roomDtoList;
	
	}

	public RoomEntity roomDtoToRoomEntity(RoomDto roomDto) {
		return this.modelMapper.map(roomDto, RoomEntity.class);
	}

	public RoomDto roomEntityToRoomDto(RoomEntity roomEntity) {
		return this.modelMapper.map(roomEntity, RoomDto.class);
	}

}
