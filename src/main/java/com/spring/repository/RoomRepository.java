package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.FloorEntity;
import com.spring.entity.RoomEntity;
import java.util.*;

public interface RoomRepository  extends JpaRepository<RoomEntity, Integer>{
	
	    List<RoomEntity>   findByFloor(FloorEntity floor);

}
