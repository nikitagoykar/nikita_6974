package com.spring;

import java.util.List;

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

import com.spring.entity.HostelEntity;
import com.spring.repository.HostelRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HostelRepositoryTest {

	@Autowired
	private HostelRepository hostelRepository;
	
	@Test
	@Order(1)
	public void saveHostel()
	{
		HostelEntity hostel= HostelEntity.builder().hostelName("Arts").build();
		hostelRepository.save(hostel);
		Assertions.assertThat(hostel.getHostelId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getHostelById() {
		HostelEntity hostel = hostelRepository.findById(3).get();
		Assertions.assertThat(hostel.getHostelId()).isEqualTo(3);
	}
	@Test
	@Order(3)
	public void getAllHostels()
	{
		List<HostelEntity> hostel = this.hostelRepository.findAll();
		Assertions.assertThat(hostel.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	public void  updateHostelById()
	{
		HostelEntity hostel = hostelRepository.findById(1).get();
		hostel.setHostelName("BMCC");
		HostelEntity hostel1 = hostelRepository.save(hostel);
		Assertions.assertThat(hostel1.getHostelName()).isEqualTo("BMCC");
	}
}
