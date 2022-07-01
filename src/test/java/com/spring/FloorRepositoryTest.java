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

import com.spring.entity.FloorEntity;
import com.spring.repository.FloorRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FloorRepositoryTest {

	@Autowired
	private FloorRepository floorRepository;
	
	@Test
	@Order(1)
	public void saveFloor()
	{
		FloorEntity floor = FloorEntity.builder().floorName("Science Student").build();
		floorRepository.save(floor);
		Assertions.assertThat(floor.getFloorId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getFloorById() {
		FloorEntity floor = floorRepository.findById(3).get();
		Assertions.assertThat(floor.getFloorId()).isEqualTo(3);
	}
	@Test
	@Order(3)
	public void getAllFloors()
	{
		List<FloorEntity> floor = this.floorRepository.findAll();
		Assertions.assertThat(floor.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	public void  updateFloorById()
	{
		FloorEntity floor = floorRepository.findById(1).get();
		floor.setFloorName("Arts");
		FloorEntity floor1 = floorRepository.save(floor);
		Assertions.assertThat(floor1.getFloorName()).isEqualTo("Arts");
	}
}
