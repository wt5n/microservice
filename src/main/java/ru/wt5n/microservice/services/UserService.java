package ru.wt5n.microservice.services;

import ru.wt5n.microservice.models.requests.Credentials;

public interface UserService {
	String login(Credentials credentials);
}
