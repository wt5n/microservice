package ru.wt5n.microservice.models.requests;

import lombok.Value;

@Value
public class Credentials {
	String username;
	String password;
}
