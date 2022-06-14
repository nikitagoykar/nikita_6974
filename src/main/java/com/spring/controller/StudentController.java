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
import com.spring.payload.StudentDto;
import com.spring.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/hostel/{hostelId}/warden/{wardenId}/floor/{floorId}/room/{roomId}/student")
	private ResponseEntity<StudentDto> createStuddent(@PathVariable int hostelId, @PathVariable int wardenId,
			@PathVariable int floorId, @PathVariable int roomId, @RequestBody StudentDto studentDto)
	{
		StudentDto createdStudent = this.studentService.createStudent(studentDto, wardenId, floorId,roomId);
		return new ResponseEntity<StudentDto>(createdStudent, HttpStatus.CREATED);
	}

	@GetMapping("/student/{StudentId}")
	private ResponseEntity<StudentDto> getStudentById(@PathVariable int studentId) {
		StudentDto getStudent = this.studentService.getStudentById(studentId);
		return new ResponseEntity<StudentDto>(getStudent, HttpStatus.OK);
	}

	@GetMapping("/student")
	ResponseEntity<List<StudentDto>> createStudent() {
		List<StudentDto> recivedAllStudents = this.studentService.getAllStudents();
		return new ResponseEntity<List<StudentDto>>(recivedAllStudents, HttpStatus.OK);
	}

	@PutMapping("/student/{studentId}")
	ResponseEntity<StudentDto> updateFloorById(@RequestBody StudentDto StudentDto, @PathVariable int studentId) {
		StudentDto updateStudent = this.studentService.updateStudentById(StudentDto, studentId);
		return new ResponseEntity<StudentDto>(updateStudent, HttpStatus.OK);
	}

	@DeleteMapping("/student/{studentId}")
	ResponseEntity<ApiResponce> deletestudent(@PathVariable int studentId) {
		this.studentService.deleteStudentById(studentId);

		ApiResponce responce = new ApiResponce();
		responce.setMessage("student deleted suceesfully");
		responce.setSucess(true);

		return new ResponseEntity<ApiResponce>(responce, HttpStatus.OK);
	}

}
