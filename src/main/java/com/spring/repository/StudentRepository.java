package com.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.entity.FloorEntity;
import com.spring.entity.RoomEntity;
import com.spring.entity.StudentEntity;
import com.spring.entity.WardenEntity;

public interface StudentRepository  extends JpaRepository<StudentEntity, Integer>{
	
	List<StudentEntity>   findByWarden(WardenEntity warden);
	List<StudentEntity>   findByFloor(FloorEntity floor);
	List<StudentEntity>   findByRoom(RoomEntity room);
	
	Optional<StudentEntity> findBystudentName(String name);
	
	@Query(value = "SELECT * FROM student_entity stud WHERE stud.warden_warden_id = :wardenid",nativeQuery = true)
	public List<StudentEntity> getStudentEntityByWardenId(@Param("wardenid") int wardenid);
	
	@Query(value = "SELECT * FROM student_entity stud WHERE stud.floor_floor_id = :floorid",nativeQuery = true)
	public List<StudentEntity> getStudentEntityByFloorlId(@Param("floorid") int floorid);
	
	@Query(value = "SELECT * FROM student_entity stud WHERE stud.room_room_id = :roomid",nativeQuery = true)
	public List<StudentEntity> getStudentEntityByRoomId(@Param("roomid") int roomid);
}
