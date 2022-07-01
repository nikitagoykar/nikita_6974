package com.spring.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.AdminEntity;
import com.spring.entity.HostelEntity;
import com.spring.exception.ResourceNotFoundException;
import com.spring.payload.AdminDto;
import com.spring.payload.HostelDto;
import com.spring.repository.AdminRepository;
import com.spring.repository.HostelRepository;
import com.spring.service.HostelService;

@Service
public class HostelServiceImplementation implements HostelService {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	HostelRepository hostelRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public HostelDto createHostel(HostelDto hostelDto, int adminId) {
		// TODO Auto-generated method stub
		AdminEntity admin = this.adminRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin", "AdminId", adminId));

		hostelDto.setAdmin(this.modelMapper.map(admin, AdminDto.class));

		
		HostelEntity savedHostel = this.hostelRepository.save(this.modelMapper.map(hostelDto, HostelEntity.class));

		return this.modelMapper.map(savedHostel, HostelDto.class);
	}

	@Override
	public List<HostelDto> getAllHostels() {
		// TODO Auto-generated method stub
		List<HostelEntity> hostelList = this.hostelRepository.findAll();
		List<HostelDto> hostelDtoList = hostelList.stream()
				.map(hostel -> this.modelMapper.map(hostel, HostelDto.class)).collect(Collectors.toList());
		return hostelDtoList;
	}

	@Override
	public HostelDto getHostelById(int hostelId) {
		// TODO Auto-generated method stub
		HostelEntity hostelEntity = this.hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel", "HostelId", hostelId));
		return this.modelMapper.map(hostelEntity,HostelDto.class);
	}

	@Override
	public HostelDto updateHostelById(HostelDto hostelDto, int hostelId) {
		// TODO Auto-generated method stub
		HostelEntity hostelEntity = this.hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel", "HostelId", hostelId));

		hostelEntity.setHostelId(hostelId);
		hostelEntity.setHostelName(hostelDto.getHostelName());
		this.hostelRepository.save(hostelEntity);
		return this.modelMapper.map(hostelEntity,HostelDto.class);
	}

	@Override
	public void deleteHostelById(int hostelId) {
		// TODO Auto-generated method stub
		HostelEntity hostelEntity = this.hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("hostel", "HostelId", hostelId));
		this.hostelRepository.delete(hostelEntity);

	}

	@Override
	public List<HostelDto> getAllHostelsByAdminId(int adminId) {
		// TODO Auto-generated method stub
		List<HostelEntity> hostel = this.hostelRepository.getHostelEntityByAdminId(adminId);
		List<HostelDto> hostelDto = hostel.stream().map(hostels -> this.modelMapper.map(hostels,HostelDto.class)).collect(Collectors.toList());
		return hostelDto;
	}

	public HostelEntity hostelDtoToHostelEntity(HostelDto hostelDto) {
		return this.modelMapper.map(hostelDto, HostelEntity.class);
	}

	public HostelDto hostelEntityToHostelDto(HostelEntity hostelEntity) {
		return this.modelMapper.map(hostelEntity, HostelDto.class);
	}

}
