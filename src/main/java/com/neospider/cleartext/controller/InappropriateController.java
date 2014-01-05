package com.neospider.cleartext.controller;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neospider.cleartext.service.InappropriateResult;
import com.neospider.cleartext.service.InappropriateService;
import com.neospider.cleartext.service.User;
import com.neospider.cleartext.service.UserService;

@Controller
@RequestMapping({ "/secure/inappropriate" })
public class InappropriateController {

	public static final String DEFAULT_PAGE = "/inappropriate/index";

	private static final Logger logger = Logger.getLogger(InappropriateController.class);

	@Resource
	private InappropriateService inappropriateService;

	@Resource
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(ModelMap model, Principal principal) {
		logger.info("callback page get request");

		User user = userService.getOrCreateUser(principal);
		if (user == null) {
			model.put("error", "Plase login before continuing.");
			logger.error("Plase login before continuing.");
		}
		String username = user.getUsername();
		model.put("username", username);
		logger.info("username:" + username);

		try {
			InappropriateResult result = inappropriateService.checkForInappropriateMessages(user);
			model.put("abusefound", result.isAbuseFound());
			logger.info("result.isAbuseFound():" + result.isAbuseFound());
			model.put("owner", result.getOwnerTelephone());
			logger.info("result.getOwnerTelephone():" + result.getOwnerTelephone());
		} catch (Exception e) {
			model.put("error", e.getMessage());
			logger.error(e.getMessage());
		}

		return DEFAULT_PAGE;
	}

}
