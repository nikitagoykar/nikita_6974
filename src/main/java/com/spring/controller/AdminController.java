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
import com.spring.payload.AdminDto;
import com.spring.service.AdminService;


@RestController
@RequestMapping("/api")

public class AdminController {


	@Autowired
	private AdminService adminService; 

	// add Admin 
	@PostMapping("/admin")
	private ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto adminDto) {
		AdminDto createdAdmin = this.adminService.createAdmin(adminDto);
		return new ResponseEntity<AdminDto>(createdAdmin, HttpStatus.CREATED);
	}

	// Fetch Admin record by AdminById
	@GetMapping("/admin/{adminId}")
	ResponseEntity<AdminDto> createHostel(@PathVariable int adminId) {
		AdminDto recivedAdmin = this.adminService.getAdminById(adminId);
		return new ResponseEntity<AdminDto>(recivedAdmin, HttpStatus.OK);
	}

	//Fetch all Admin record by AllAdmins
	@GetMapping("/admin")
	ResponseEntity<List<AdminDto>> createAdmin() {
		List<AdminDto> recivedAllAdmins = this.adminService.getAllAdmins();
		return new ResponseEntity<List<AdminDto>>(recivedAllAdmins, HttpStatus.OK);
	}

	// update admin record AdminById
	@PutMapping("/admin/{adminId}")
	ResponseEntity<AdminDto> updateAdminById(@RequestBody AdminDto adminDto, @PathVariable int adminId) {
		AdminDto updatedAdmin = this.adminService.updateAdminById(adminDto, adminId);
		return new ResponseEntity<AdminDto>(updatedAdmin, HttpStatus.OK);
	}
	
	//delete admin record by adminId
	@DeleteMapping("/admin/{adminId}")
	ResponseEntity<ApiResponce>deleteAdmin(@PathVariable int adminId)
	{
		this.adminService.deleteAdminById(adminId);
		ApiResponce responce = new ApiResponce();
		responce.setMessage("Admin deleted succesfully");
		responce.setSucess(true);
		return new ResponseEntity<ApiResponce>(responce,HttpStatus.OK);
	}
	}