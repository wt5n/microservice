package ru.wt5n.microservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.wt5n.microservice.models.requests.Credentials;
import ru.wt5n.microservice.services.UserService;

@RestController
@Tag(name = "user", description = "Login and registration")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@Operation(summary = "Login service. Returns token.")
	@PostMapping("/login")
	@SneakyThrows
	public String login(@RequestBody Credentials credentials) {
		return userService.login(credentials);
	}
}