package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.FloorEntity;
import com.spring.entity.RoomEntity;
import com.spring.entity.StudentEntity;
import com.spring.entity.WardenEntity;

public interface StudentRepository  extends JpaRepository<StudentEntity, Integer>{
	
	List<StudentEntity>   findByWarden(WardenEntity warden);
	List<StudentEntity>   findByFloor(FloorEntity floor);
	List<StudentEntity>   findByRoom(RoomEntity room);
}
