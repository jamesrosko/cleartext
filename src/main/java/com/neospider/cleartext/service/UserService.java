package com.neospider.cleartext.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.att.api.oauth.OAuthToken;

@Service
public class UserService {
	private static final Logger logger = Logger.getLogger(UserService.class);

	private static Map<String, User> users = new HashMap<String, User>();

	public UserService() {
		User user = new User();
		user.setUsername("james");
		user.setParentAddr("4259193066");
		user.setKidAddr("4254060395");
		addUser(user);
	}

	public User getOrCreateUser(Principal principal) {
		String username = principal.getName();
		logger.info("principal username:" + username);
		if (!userExists(username)) {
			User user = new User();
			user.setUsername(username);
			addUser(user);
		}
		User user = getUser(username);
		return user;
	}

	public User getUser(String username) {
		return users.get(username.toLowerCase());
	}

	public boolean userExists(String username) {
		return users.get(username.toLowerCase()) != null ? true : false;
	}

	public void addUser(User user) {
		if (getUser(user.getUsername()) == null) {
			String username = user.getUsername().toLowerCase();
			logger.info("adding user:" + username);
			users.put(username, user);
		}
	}

	public void setCode(String username, String code) {
		if (userExists(username)) {
			User user = getUser(username);
			user.setCode(code);
			users.put(username, user);
		}
	}

	public void setToken(String username, OAuthToken token) {
		if (userExists(username)) {
			User user = getUser(username);
			user.setToken(token);
			users.put(username, user);
		}
	}

	public void setParentAddr(String username, String parentaddr) {
		if (userExists(username)) {
			User user = getUser(username);
			user.setParentAddr(parentaddr);
			users.put(username, user);
		}
	}

	public void setKidAddr(String username, String kidaddr) {
		if (userExists(username)) {
			User user = getUser(username);
			user.setKidAddr(kidaddr);
			users.put(username, user);
		}
	}

	public List<User> getAllUser() {
		return new ArrayList<User>(users.values());
	}

}
