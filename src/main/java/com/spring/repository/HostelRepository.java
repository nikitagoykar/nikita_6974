package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.entity.HostelEntity;

public interface HostelRepository  extends JpaRepository<HostelEntity, Integer>{
//
//@Query(value = "SELECT * FROM company_entity comp WHERE comp.student_student_id = :studid",nativeQuery = true)
//	
//	public List<CompanyEntity> getCompanyEntityByStudid( @Param("studid") int studid);

	@Query(value = "SELECT * FROM hostel_entity host WHERE host. admin_admin_id = :adminid",nativeQuery = true)
	public List<HostelEntity> getHostelEntityByAdminId (@Param("adminid") int adminid);
}
