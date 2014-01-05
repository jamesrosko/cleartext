package com.neospider.cleartext.controller;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neospider.cleartext.service.User;
import com.neospider.cleartext.service.UserService;

@Controller
@RequestMapping({ "/redirect" })
public class AuthRedirectController {

	public static final String DEFAULT_PAGE = "/redirect/index";

	private static final Logger logger = Logger.getLogger(AuthRedirectController.class);

	@Resource
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(ModelMap model, @RequestParam String code, Principal principal) {
		logger.info("new code retrieved:" + code);
		model.put("code", code);

		User user = userService.getOrCreateUser(principal);
		if (user == null) {
			String error = "new code retrieved but the user is not logged in";
			model.put("error", error);
			logger.error(error);
		} else {
			String username = user.getUsername();
			userService.setCode(username, code);
			logger.info("user:" + username);
			model.put("username", username);
			model.put("code", code);
		}

		return DEFAULT_PAGE;
	}
}
