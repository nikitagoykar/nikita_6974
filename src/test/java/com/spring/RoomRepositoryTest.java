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

import com.spring.entity.RoomEntity;
import com.spring.repository.RoomRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoomRepositoryTest {

	
	@Autowired
	private RoomRepository roomRepository;
	
	@Test
	@Order(1)
	public void saveRoom()
	{
		RoomEntity room= RoomEntity.builder().roomType("6 bases").build();
		roomRepository.save(room);
		Assertions.assertThat(room.getRoomId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getRoomById() {
		RoomEntity room = roomRepository.findById(3).get();
		Assertions.assertThat(room.getRoomId()).isEqualTo(3);
	}
	@Test
	@Order(3)
	public void getAllRooms()
	{
		List<RoomEntity> room = this.roomRepository.findAll();
		Assertions.assertThat(room.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	public void  updateRoomById()
	{
		RoomEntity room = roomRepository.findById(1).get();
		room.setRoomType("2 bases");
		RoomEntity room1 = roomRepository.save(room);
		Assertions.assertThat(room1.getRoomType()).isEqualTo("2 bases");
	}
}
