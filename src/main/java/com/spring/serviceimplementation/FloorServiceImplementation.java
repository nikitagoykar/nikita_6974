package com.spring.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.FloorEntity;
import com.spring.entity.WardenEntity;
import com.spring.exception.ResourceNotFoundException;
import com.spring.payload.FloorDto;
import com.spring.payload.WardenDto;
import com.spring.repository.FloorRepository;
import com.spring.repository.RoomRepository;
import com.spring.repository.StudentRepository;
import com.spring.repository.WardenRepository;
import com.spring.service.FloorService;

@Service
public class FloorServiceImplementation implements FloorService {
	@Autowired
	private FloorRepository floorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private WardenRepository wardenRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public FloorDto createFloor(FloorDto floorDto, int wardenId) {
		// TODO Auto-generated method stub
		WardenEntity wardenEntity = this.wardenRepository.findById(wardenId)
				.orElseThrow(() -> new ResourceNotFoundException("Warden", "WardenId", wardenId));

		floorDto.setWarden(this.modelMapper.map(wardenEntity, WardenDto.class));
		FloorEntity saveFloor = this.floorRepository.save(this.modelMapper.map(floorDto, FloorEntity.class));
		return this.modelMapper.map(saveFloor,FloorDto.class);
//		FloorEntity floorEntity = this.floorDtoToFloorEntity(floorDto);
//
//		FloorEntity newFloorEntity = this.floorRepository.save(this.floorDtoToFloorEntity(floorDto));
//		return this.floorEntityToFloorDto(newFloorEntity);
		
		
	}

	@Override
	public FloorDto getFloorById(int floorId) {
		// TODO Auto-generated method stub
		FloorEntity floor = this.floorRepository.findById(floorId)
				.orElseThrow(() -> new ResourceNotFoundException("Floor", "FloorId", floorId));

		return this.modelMapper.map(floor, FloorDto.class);
	}

	@Override
	public List<FloorDto> getAllFloors() {
		// TODO Auto-generated method stub
		List<FloorDto> getAllFloors = this.floorRepository.findAll().stream()
				.map(floor -> this.floorEntityToFloorDto(floor)).collect(Collectors.toList());
		return getAllFloors;
	}

	@Override
	public List<FloorDto> getAllFloorsByWardenId(int wardenId) {
		// TODO Auto-generated method stub
		List<FloorEntity> floor = this.floorRepository.getFloorEntityByWardenId(wardenId);
		System.out.println("list of floor"+floor);
		List<FloorDto> floorDto = floor.stream().map(floors -> this.modelMapper.map(floors, FloorDto.class)).collect(Collectors.toList());
		return floorDto;
	}
	
	@Override
	public FloorDto updateFloorById(FloorDto floorDto, int floorId) {
		// TODO Auto-generated method stub
		 FloorEntity floorEntity = this.floorRepository.findById(floorId)
				.orElseThrow(() -> new ResourceNotFoundException("Floor", "FloorId", floorId));
		FloorEntity updateFloorEntity = this.floorRepository.save(this.floorDtoToFloorEntity(floorDto));
		return this.floorEntityToFloorDto(updateFloorEntity);
	}

	@Override
	public void deleteFloorById(int floorId) {
		// TODO Auto-generated method stub
		FloorEntity floorEntity = this.floorRepository.findById(floorId)
				.orElseThrow(() -> new ResourceNotFoundException("Floor", "FloorId", floorId));
		this.floorRepository.delete(floorEntity);
	}

	public FloorEntity floorDtoToFloorEntity(FloorDto floorDto) {
		return this.modelMapper.map(floorDto, FloorEntity.class);
	}

	public FloorDto floorEntityToFloorDto(FloorEntity floorEntity) {
		return this.modelMapper.map(floorEntity, FloorDto.class);
	}



}
