package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.entity.FloorEntity;

public interface FloorRepository  extends JpaRepository<FloorEntity, Integer>{
	//List<FloorEntity>   findByRoom(RoomEntity,);
	
	
	@Query(value = "SELECT * FROM floor_entity floor WHERE floor. warden_warden_id = :wardenid",nativeQuery = true)
	public List<FloorEntity> getFloorEntityByWardenId(@Param("wardenid") int wardenid);
}
