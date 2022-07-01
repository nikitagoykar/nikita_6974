package com.spring.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.AdminEntity;
import com.spring.exception.ResourceNotFoundException;
import com.spring.payload.AdminDto;
import com.spring.repository.AdminRepository;
import com.spring.service.AdminService;

@Service
public class AdminServiceImplementation  implements AdminService{



	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	

	@Override
	public AdminDto createAdmin(AdminDto adminDto) {
		// TODO Auto-generated method stub
		AdminEntity adminEntity = this.modelMapper.map(adminDto,AdminEntity.class);
		AdminEntity saveAdmin = this.adminRepository.save(adminEntity);
		return this.modelMapper.map(saveAdmin,AdminDto.class);
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
				.map(admin -> this.modelMapper.map(admin,AdminDto.class)).collect(Collectors.toList());
		return getAllAdmins;
	}

	@Override
	public AdminDto updateAdminById(AdminDto adminDto, int adminId) {
		// TODO Auto-generated method stub
		AdminEntity adminEntity = this.adminRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin", "AdminId", adminId));
		AdminEntity updatedAdminEntity = this.modelMapper.map(adminDto, AdminEntity.class);
		
		AdminEntity saveAdmin = this.adminRepository.save(updatedAdminEntity);

		return this.modelMapper.map(saveAdmin,AdminDto.class);
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
