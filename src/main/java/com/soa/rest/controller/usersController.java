package com.soa.rest.controller;

import com.soa.rest.model.Users;
import com.soa.rest.repository.UserJpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class usersController {

	@Autowired
	private UserJpaRepository userJpaRepository;

	@GetMapping(value = "/all")
	public List<Users> findAll() {
		return userJpaRepository.findAll();
		
	}

	@GetMapping(value = "{name}")
	public Users findUserByName(@PathVariable final String name) {
		return userJpaRepository.findByName(name);
	}

	@GetMapping(value = "/user")
	public Optional<Users> findById(@RequestParam("id") Long id) {
		Optional<Users> userOptional = userJpaRepository.findById(id);
		return userOptional;
	}

	@GetMapping(value = "/user/{id}")
	public Optional<Users> findByTemplateId(@PathVariable Long id) {
		Optional<Users> userOptional = userJpaRepository.findById(id);
		return userOptional;
	}

	@PostMapping(value = "/insert")
	public Users load(@RequestBody final Users users) {
		userJpaRepository.save(users);
		return userJpaRepository.findByName(users.getName());
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable long id) {
		userJpaRepository.deleteById(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> update(@RequestBody Users user, @PathVariable long id) {

		Optional<Users> userOptional = userJpaRepository.findById(id);

		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		userJpaRepository.save(user);
		return ResponseEntity.noContent().build();
	}
}
