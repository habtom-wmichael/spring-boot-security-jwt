package com.yadu.securedapp.secured.spring.boot.app;

import com.yadu.securedapp.secured.spring.boot.app.models.UserCredential;
import com.yadu.securedapp.secured.spring.boot.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SecuredSpringBootAppApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(SecuredSpringBootAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<UserCredential> userCredentials= new ArrayList<>();
		UserCredential habtom= new UserCredential("habtom","habtom");
		UserCredential yadu= new UserCredential("yadu","yadu");
		UserCredential bb= new UserCredential("bb","bb");

		userCredentials.add(habtom);
		userCredentials.add(yadu);
		userCredentials.add(bb);
		userCredentials.forEach(u->userService.save(u));
	}
}
