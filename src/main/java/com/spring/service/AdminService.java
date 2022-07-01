package com.spring.service;

import java.util.List;

import com.spring.payload.AdminDto;

public interface AdminService {
public AdminDto createAdmin(AdminDto adminDto,int hostelId);
	
	public AdminDto getAdminById(int adminId);
	
	public List<AdminDto> getAllAdmins();
	
	public AdminDto updateAdminById(AdminDto adminDto,int adminId);
	
	public void deleteAdminById(int adminId);

}
