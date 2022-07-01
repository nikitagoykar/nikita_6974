package com.spring;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.entity.StudentEntity;
import com.spring.repository.StudentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StuduentRepositoryTest {
	@Autowired
	private StudentRepository studentRepository;

	@Test
	@Order(1)
	public void saveStudent() {
		StudentEntity student = StudentEntity.builder().studentName("nikita").studentEmail("nikita@gmail.com")
				.studentAddress("pune").studentContact("9874563210").build();
		studentRepository.save(student);
		Assertions.assertThat(student.getStudentId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getStudentById() {
		StudentEntity student = studentRepository.findById(3).get();
		Assertions.assertThat(student.getStudentId()).isEqualTo(3);
	}
	@Test
	@Order(3)
	public void getAllStudents()
	{
		List<StudentEntity> student = this.studentRepository.findAll();
		Assertions.assertThat(student.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	public void  updateStudentById()
	{
		StudentEntity student = studentRepository.findById(1).get();
		student.setStudentAddress("Mumbai");
		StudentEntity student1 = studentRepository.save(student);
		Assertions.assertThat(student1.getStudentAddress()).isEqualTo("Mumbai");
	}
	@Test
	@Order(5)
	public void deleteStudent() 
	{
		StudentEntity student = studentRepository.findById(1).get();
		studentRepository.delete(student);
		StudentEntity student1 = null;
		Optional<StudentEntity> studentOptional = studentRepository.findBystudentName("nikita");
		if(studentOptional.isPresent())
		{
			student1 = studentOptional.get();
		}
	}
}
