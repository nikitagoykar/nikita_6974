package com.spring.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.HostelEntity;
import com.spring.entity.StudentEntity;
import com.spring.entity.WardenEntity;
import com.spring.exception.ResourceNotFoundException;
import com.spring.payload.StudentDto;
import com.spring.payload.WardenDto;
import com.spring.repository.HostelRepository;
import com.spring.repository.StudentRepository;
import com.spring.repository.WardenRepository;
import com.spring.service.WardenService;

@Service
public class WardenServiceImplementation implements WardenService {
	@Autowired
	private WardenRepository wardenRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private HostelRepository hostelRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public WardenDto createWarden(WardenDto wardenDto, int hostelId) {
		// TODO Auto-generated method stub
		HostelEntity hostelEntity = this.hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel", "HostelId", hostelId));

		WardenEntity wardernEntity = this.modelMapper.map(wardenDto, WardenEntity.class);

		hostelEntity.getWarden().add(wardernEntity);
		wardernEntity.setHostel(hostelEntity);

		
		
		//StudentDto studentDto = this.modelMapper.map(studentEntity, StudentDto.class);

		
		
		// WardenEntity savedWardenEntity  = this.wardenRepository.save(wardernEntity);
		

		return this.wardenEntityToWardenDto(this.wardenRepository.save(wardernEntity));
	}

	@Override
	public WardenDto getWardenById(int wardenId) {
		// TODO Auto-generated method stub
		WardenEntity warden = this.wardenRepository.findById(wardenId)
				.orElseThrow(() -> new ResourceNotFoundException("Warden", "WardenId", wardenId));
		return this.modelMapper.map(warden, WardenDto.class);
	}

	@Override
	public List<WardenDto> getAllWardens() {
		// TODO Auto-generated method stub
		List<WardenDto> getAllWardens = this.wardenRepository.findAll().stream()
				.map(warden -> this.wardenEntityToWardenDto(warden)).collect(Collectors.toList());
		return getAllWardens;
	}

	@Override
	public WardenDto updatedWardenById(WardenDto wardenDto, int wardenId) {
		// TODO Auto-generated method stub
		WardenEntity wardenEntity = this.wardenRepository.findById(wardenId)
				.orElseThrow(() -> new ResourceNotFoundException("Warden", "WardenId", wardenId));
		WardenEntity updatedWardenEntity = this.wardenRepository.save(this.wardenDtoToWardenEntity(wardenDto));

		return this.wardenEntityToWardenDto(updatedWardenEntity);
	}

	@Override
	public void deleteWardenById(int wardenId) {
		// TODO Auto-generated method stub
		WardenEntity wardenEntity = this.wardenRepository.findById(wardenId)
				.orElseThrow(() -> new ResourceNotFoundException("Warden", "WardenId", wardenId));
		this.wardenRepository.delete(wardenEntity);

	}

	public WardenEntity wardenDtoToWardenEntity(WardenDto wardenEntDto) {
		return this.modelMapper.map(wardenEntDto, WardenEntity.class);
	}

	public WardenDto wardenEntityToWardenDto(WardenEntity wardenEntity) {
		return this.modelMapper.map(wardenEntity, WardenDto.class);
	}

}
