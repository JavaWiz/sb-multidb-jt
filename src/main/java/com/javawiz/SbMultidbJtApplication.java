package com.javawiz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.javawiz.model.User;
import com.javawiz.repository.UserRepository;

@SpringBootApplication
public class SbMultidbJtApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(SbMultidbJtApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SbMultidbJtApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner userEntity(UserRepository userRepository) {
		return (args) -> {

			List<User> users = userRepository.findAll();
			users.forEach(user -> {
				logger.debug("{}", user);
			});

			User user = userRepository.findUserById(100);
			logger.debug("{}", user);

			User insert = User.builder().name("John").email("john@gmail.com").build();

			User savedUser = userRepository.create(insert);
			User newUser = userRepository.findUserById(savedUser.getUid());
			logger.debug("{}", newUser);
		};
	}
}