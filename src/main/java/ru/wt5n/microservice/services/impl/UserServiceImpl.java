package ru.wt5n.microservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.wt5n.microservice.models.entity.User;
import ru.wt5n.microservice.models.requests.Credentials;
import ru.wt5n.microservice.repositories.UserRepository;
import ru.wt5n.microservice.services.UserService;
import ru.wt5n.microservice.utils.JwtTokenUtil;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository usersRepository;
	private final JwtTokenUtil jwtTokenUtil;

	public String login(Credentials credentials) {
		User user = usersRepository.findByUsername(credentials.getUsername())
				.orElseThrow(() -> new AccessDeniedException("User with username " + credentials.getUsername() + " not found"));

		if (!user.isEnabled()) {
			throw new AccessDeniedException("Your account is not active");
		}
		log.info("User {} logged in successfully", user.getUsername());
		String token = jwtTokenUtil.generateToken(user.getUsername());
		return "Token: " + token;
	}
}
