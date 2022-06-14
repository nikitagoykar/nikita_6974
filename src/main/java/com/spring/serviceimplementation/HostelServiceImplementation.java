package com.spring.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.HostelEntity;
import com.spring.exception.ResourceNotFoundException;
import com.spring.payload.HostelDto;
import com.spring.repository.HostelRepository;
import com.spring.service.HostelService;

@Service
public class HostelServiceImplementation implements HostelService {

	@Autowired
	HostelRepository hostelRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public HostelDto createHostel(HostelDto hostelDto) {
		// TODO Auto-generated method stub
		HostelEntity hostelEntity = this.hostelDtoToHostelEntity(hostelDto);
		HostelEntity savedHostel = this.hostelRepository.save(hostelEntity);

		return this.hostelEntityToHostelDto(savedHostel);
	}

	@Override
	public List<HostelDto> getAllHostels() {
		// TODO Auto-generated method stub
		List<HostelEntity> hostelList = this.hostelRepository.findAll();
		List<HostelDto> hostelDtoList = hostelList.stream().map(hostel -> this.hostelEntityToHostelDto(hostel))
				.collect(Collectors.toList());
		return hostelDtoList;
	}

	@Override
	public HostelDto getHostelById(int hostelId) {
		// TODO Auto-generated method stub
		HostelEntity hostelEntity = this.hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel", "HostelId", hostelId));
		return this.hostelEntityToHostelDto(hostelEntity);
	}

	@Override
	public HostelDto updateHostelById(HostelDto hostelDto, int hostelId) {
		// TODO Auto-generated method stub
		HostelEntity hostelEntity = this.hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel", "HostelId", hostelId));
		
		hostelEntity.setHostelId(hostelId);
		hostelEntity.setHostelName(hostelDto.getHostelName());
		this.hostelRepository.save(hostelEntity);
		return this.hostelEntityToHostelDto(hostelEntity);
	}

	@Override
	public void deleteHostelById(int hostelId) {
		// TODO Auto-generated method stub
		HostelEntity hostelEntity = this.hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("hostel", "HostelId", hostelId));
    this.hostelRepository.delete(hostelEntity);
		
	}

	public HostelEntity hostelDtoToHostelEntity(HostelDto hostelDto) {
		return this.modelMapper.map(hostelDto, HostelEntity.class);
	}

	public HostelDto hostelEntityToHostelDto(HostelEntity hostelEntity) {
		return this.modelMapper.map(hostelEntity, HostelDto.class);
	}
}
