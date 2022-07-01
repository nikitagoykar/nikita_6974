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

import com.spring.entity.AdminEntity;
import com.spring.repository.AdminRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminRepositoryTest {

	@Autowired
	private AdminRepository adminRepository;
	
	@Test
	@Order(1)
	public void saveAdmin()
	{
		AdminEntity admin= AdminEntity.builder().adminName("nikita").password("12345").build();
		adminRepository.save(admin);
		Assertions.assertThat(admin.getAdminId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getAdminById() {
		AdminEntity admin = adminRepository.findById(3).get();
		Assertions.assertThat(admin.getAdminId()).isEqualTo(3);
	}
	@Test
	@Order(3)
	public void getAllAdmins()
	{
		List<AdminEntity> admin = this.adminRepository.findAll();
		Assertions.assertThat(admin.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	public void  updateAdminById()
	{
		AdminEntity admin = adminRepository.findById(1).get();
		admin.setAdminName("Komal");
		AdminEntity admin1 = adminRepository.save(admin);
		Assertions.assertThat(admin1.getAdminName()).isEqualTo("Komal");
	}
}
