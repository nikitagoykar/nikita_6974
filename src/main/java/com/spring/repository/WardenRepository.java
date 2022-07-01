package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.entity.WardenEntity;

public interface WardenRepository extends JpaRepository<WardenEntity, Integer> {


	@Query(value = "SELECT * FROM warden_entity warden WHERE warden.  hostel_hostel_id = :hostelid",nativeQuery = true)
	public List<WardenEntity> getWardenEntityByhostelId(@Param("hostelid") int hostelid);	
	 
	
}
