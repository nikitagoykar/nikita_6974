package com.spring.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.HostelEntity;
import com.spring.entity.WardenEntity;
import com.spring.exception.ResourceNotFoundException;
import com.spring.payload.HostelDto;
import com.spring.payload.WardenDto;
import com.spring.repository.HostelRepository;
import com.spring.repository.StudentRepository;
import com.spring.repository.WardenRepository;
import com.spring.service.WardenService;

@Service
public class WardenServiceImplementation implements WardenService {
	@Autowired
	private WardenRepository wardenRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private HostelRepository hostelRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public WardenDto createWarden(WardenDto wardenDto, int hostelId) {
		// TODO Auto-generated method stub
		HostelEntity hostelEntity = this.hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel", "HostelId", hostelId));

//		WardenEntity wardernEntity = this.modelMapper.map(wardenDto, WardenEntity.class);
//         WardenDto saveWarden = this.wardenRepository.save(war)
		wardenDto.setHostel(this.modelMapper.map(hostelEntity,HostelDto.class));
		
		//hostelEntity.getWarden().add(wardernEntity);
		//wardernEntity.setHostel(hostelEntity);

		WardenEntity saveWarden = this.wardenRepository.save(this.modelMapper.map(wardenDto, WardenEntity.class));
		return this.modelMapper.map(saveWarden,WardenDto.class);
	}

	@Override
	public WardenDto getWardenById(int wardenId) {
		// TODO Auto-generated method stub
		WardenEntity warden = this.wardenRepository.findById(wardenId)
				.orElseThrow(() -> new ResourceNotFoundException("Warden", "WardenId", wardenId));
		return this.modelMapper.map(warden, WardenDto.class);
	}

	@Override
	public List<WardenDto> getAllWardens() {
		// TODO Auto-generated method stub
		List<WardenDto> getAllWardens = this.wardenRepository.findAll().stream()
				.map(warden -> this.modelMapper.map(warden, WardenDto.class)).collect(Collectors.toList());
		return getAllWardens;
	}
	@Override
	public List<WardenDto> getWardenByHostelId(int hostelId) {
		// TODO Auto-generated method stub
		List<WardenEntity> warden = this.wardenRepository.getWardenEntityByhostelId(hostelId);
		List<WardenDto> wardenDto = warden.stream().map(wardens -> this.modelMapper.map(wardens, WardenDto.class)).collect(Collectors.toList());
		return wardenDto;
	}

	@Override
	public WardenDto updatedWardenById(WardenDto wardenDto, int wardenId) {
		// TODO Auto-generated method stub
		WardenEntity wardenEntity = this.wardenRepository.findById(wardenId)
				.orElseThrow(() -> new ResourceNotFoundException("Warden", "WardenId", wardenId));
		WardenEntity updatedWardenEntity = this.modelMapper.map(wardenDto, WardenEntity.class);

		WardenEntity saveWarden = this.wardenRepository.save(updatedWardenEntity);
		return this.modelMapper.map(saveWarden, WardenDto.class);
	}

	@Override
	public void deleteWardenById(int wardenId) {
		// TODO Auto-generated method stub
		WardenEntity wardenEntity = this.wardenRepository.findById(wardenId)
				.orElseThrow(() -> new ResourceNotFoundException("Warden", "WardenId", wardenId));
		this.wardenRepository.delete(wardenEntity);

	}

	public WardenEntity wardenDtoToWardenEntity(WardenDto wardenEntDto) {
		return this.modelMapper.map(wardenEntDto, WardenEntity.class);
	}

	public WardenDto wardenEntityToWardenDto(WardenEntity wardenEntity) {
		return this.modelMapper.map(wardenEntity, WardenDto.class);
	}

	

}
