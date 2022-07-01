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

import com.spring.entity.WardenEntity;
import com.spring.repository.WardenRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WardenRepositoryTest {
	
	@Autowired
	private WardenRepository wardenRepository;
	
	@Test
	@Order(1)
	public void saveWarden()
	{
		WardenEntity warden= WardenEntity.builder().wardenName("Mr.joshi").wardenContact("9874563021").build();
		wardenRepository.save(warden);
		Assertions.assertThat(warden.getWardenId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getWardenById() {
		WardenEntity warden = wardenRepository.findById(3).get();
		Assertions.assertThat(warden.getWardenId()).isEqualTo(3);
	}
	@Test
	@Order(3)
	public void getAllWardens()
	{
		List<WardenEntity> warden = this.wardenRepository.findAll();
		Assertions.assertThat(warden.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	public void  updateRoomById()
	{
		WardenEntity warden = wardenRepository.findById(1).get();
		warden.setWardenContact("7896541230");
		WardenEntity warden1 = wardenRepository.save(warden);
		Assertions.assertThat(warden1.getWardenContact()).isEqualTo("7896541230");
	}

}
