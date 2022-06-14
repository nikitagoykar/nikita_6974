package com.spring.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.FloorEntity;
import com.spring.entity.RoomEntity;
import com.spring.entity.StudentEntity;
import com.spring.entity.WardenEntity;
import com.spring.exception.ResourceNotFoundException;
import com.spring.payload.FloorDto;
import com.spring.payload.RoomDto;
import com.spring.payload.StudentDto;
import com.spring.payload.WardenDto;
import com.spring.repository.FloorRepository;
import com.spring.repository.HostelRepository;
import com.spring.repository.RoomRepository;
import com.spring.repository.StudentRepository;
import com.spring.repository.WardenRepository;
import com.spring.service.StudentService;

@Service
public class StudentServiceImplementation implements StudentService {

	@Autowired
	private HostelRepository hostelRepository;
	@Autowired
	private WardenRepository wardenRepository;
	@Autowired
	private FloorRepository floorRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public StudentDto createStudent(StudentDto studentDto, int wardenId, int floorId, int roomId) {
		// TODO Auto-generated method stub
		WardenEntity wardenEntity = this.wardenRepository.findById(wardenId)
				.orElseThrow(() -> new ResourceNotFoundException("Warden", "WardenId", wardenId));

		FloorEntity floorEntity = this.floorRepository.findById(floorId)
				.orElseThrow(() -> new ResourceNotFoundException("Floor", "FloorId", floorId));

		RoomEntity roomEntity = this.roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room", "RoomId", roomId));

		roomEntity.getStudent().add(this.modelMapper.map(studentDto,StudentEntity.class));
	wardenEntity.getStudent().add(this.modelMapper.map(studentDto,StudentEntity.class));
floorEntity.getStudent().add(this.modelMapper.map(studentDto,StudentEntity.class));
	
		studentDto.setWarden(this.modelMapper.map(wardenEntity, WardenDto.class));
		studentDto.setFloor(this.modelMapper.map(floorEntity, FloorDto.class));
	studentDto.setRoom(this.modelMapper.map(roomEntity, RoomDto.class));
	StudentEntity studentEntity = this.studentRepository.save(this.studentDtoToStudentEntity(studentDto));
		return this.studentEntityToStudentDto(studentEntity);
	}

	@Override
	public StudentDto getStudentById(int studentId) {
		// TODO Auto-generated method stub
		StudentEntity studentEntity = this.studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "StudentId", studentId));

		return this.modelMapper.map(studentEntity, StudentDto.class);
	}

	@Override
	public List<StudentDto> getAllStudents() {
		// TODO Auto-generated method stub
		List<StudentDto> getAllStudent = this.studentRepository.findAll().stream()
				.map(studentEntity -> this.studentEntityToStudentDto(studentEntity)).collect(Collectors.toList());

		return getAllStudent;
	}

	@Override
	public StudentDto updateStudentById(StudentDto studentDto, int studentId) {
		// TODO Auto-generated method stub
		StudentEntity studentEntity = this.studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "StudentId", studentId));

		StudentEntity updatedStudentEntity = this.studentRepository.save(this.studentDtoToStudentEntity(studentDto));
		return this.studentEntityToStudentDto(updatedStudentEntity);
	}

	@Override
	public void deleteStudentById(int studentId) {
		// TODO Auto-generated method stub
		StudentEntity studentEntity = this.studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "StudentId", studentId));
		this.studentRepository.delete(studentEntity);

	}

	public StudentEntity studentDtoToStudentEntity(StudentDto studentDto) {
		return this.modelMapper.map(studentDto, StudentEntity.class);
	}

	public StudentDto studentEntityToStudentDto(StudentEntity studentEntity) {
		return this.modelMapper.map(studentEntity, StudentDto.class);
	}

}
