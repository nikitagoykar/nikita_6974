package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.FloorEntity;
import com.spring.entity.RoomEntity;
import com.spring.entity.WardenEntity;

public interface FloorRepository  extends JpaRepository<FloorEntity, Integer>{
	//List<FloorEntity>   findByRoom(RoomEntity,);
	
}
