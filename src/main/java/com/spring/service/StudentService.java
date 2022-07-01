package com.spring.service;

import java.util.List;

import com.spring.payload.StudentDto;

public interface StudentService {
	public StudentDto createStudent(StudentDto studentDto ,int wardenId, int floorId, int roomId);
	
	public StudentDto getStudentById(int studentId);
	
	public List<StudentDto> getAllStudents();
	
	public StudentDto updateStudentById(StudentDto studentDto,int studentId);
	
	public void deleteStudentById(int studentId);
	
	public List<StudentDto> getAllStudentsBywardenId(int wardenId);

	public List<StudentDto> getAllStudentsByFloorId(int floorId);
	
	public List<StudentDto> getAllStudentsByRoomId(int roomId);
}
