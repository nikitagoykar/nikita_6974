package com.spring.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.AdminEntity;
import com.spring.entity.FloorEntity;
import com.spring.entity.HostelEntity;
import com.spring.entity.WardenEntity;
import com.spring.exception.ResourceNotFoundException;
import com.spring.payload.AdminDto;
import com.spring.payload.FloorDto;
import com.spring.payload.WardenDto;
import com.spring.repository.AdminRepository;
import com.spring.repository.FloorRepository;
import com.spring.repository.HostelRepository;
import com.spring.repository.RoomRepository;
import com.spring.repository.StudentRepository;
import com.spring.repository.WardenRepository;
import com.spring.service.AdminService;

@Service
public class AdminServiceImplementation  implements AdminService{

	@Autowired
	private HostelRepository hostelRepository;

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	

	@Override
	public AdminDto createAdmin(AdminDto adminDto, int hostelId) {
		// TODO Auto-generated method stub
		HostelEntity hostelEntity = this.hostelRepository.findById(hostelId)
			.orElseThrow(() -> new ResourceNotFoundException("Hostel", "HostelId", hostelId));

		AdminEntity adminEntity = this.modelMapper.map(adminDto, AdminEntity.class);
		
		adminEntity.setHostel(hostelEntity);
		return this.adminEntityToAdminDto(this.adminRepository.save(adminEntity));
	}

	@Override
	public AdminDto getAdminById(int adminId) {
		// TODO Auto-generated method stub
		AdminEntity admin = this.adminRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin", "AdminId", adminId));
		return this.modelMapper.map(admin,AdminDto.class);
	}

	@Override
	public List<AdminDto> getAllAdmins() {
		// TODO Auto-generated method stub
		List<AdminDto> getAllAdmins = this.adminRepository.findAll().stream()
				.map(admin -> this.adminEntityToAdminDto(admin)).collect(Collectors.toList());
		return getAllAdmins;
	}

	@Override
	public AdminDto updateAdminById(AdminDto adminDto, int adminId) {
		// TODO Auto-generated method stub
		AdminEntity adminEntity = this.adminRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin", "AdminId", adminId));
		AdminEntity updatedAdminEntity = this.adminRepository.save(this.adminDtoToAdminEntity(adminDto));

		return this.adminEntityToAdminDto(updatedAdminEntity);
	}

	@Override
	public void deleteAdminById(int adminId) {
		// TODO Auto-generated method stub
		AdminEntity adminEntity = this.adminRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin", "AdminId", adminId));
		this.adminRepository.delete(adminEntity);
	
	}
	
	public AdminEntity adminDtoToAdminEntity(AdminDto adminDto) {
		return this.modelMapper.map(adminDto, AdminEntity.class);
	}

	public AdminDto adminEntityToAdminDto(AdminEntity adminEntity) {
		return this.modelMapper.map(adminEntity, AdminDto.class);
	}
	
	
}
