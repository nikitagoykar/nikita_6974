package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.AdminEntity;

public interface AdminRepository  extends JpaRepository<AdminEntity,Integer>{

}
