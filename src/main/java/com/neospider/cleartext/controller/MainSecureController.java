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
@RequestMapping({ "/secure/index" })
public class MainSecureController {

	public static final String DEFAULT_PAGE = "/secure/index";

	private static final Logger logger = Logger.getLogger(MainSecureController.class);

	@Resource
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(ModelMap model, Principal principal) {
		logger.info("callback page get request");

		User user = userService.getOrCreateUser(principal);

		logger.info("user:" + user);
		model.put("username", user.getUsername());
		model.put("parentaddr", user.getParentAddr());
		model.put("kidaddr", user.getKidAddr());
		model.put("code", user.getCode());

		return DEFAULT_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(ModelMap model, Principal principal, @RequestParam String parentaddr,
	    @RequestParam String kidaddr) {
		logger.info("callback page get request");

		User user = userService.getOrCreateUser(principal);
		String username = user.getUsername();

		userService.setParentAddr(username, parentaddr);
		user.setParentAddr(parentaddr);
		userService.setKidAddr(username, kidaddr);
		user.setKidAddr(kidaddr);

		model.put("notification", "Phone number updated!");
		return doGet(model, principal);
	}
}
