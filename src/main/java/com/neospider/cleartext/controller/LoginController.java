package com.neospider.cleartext.controller;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neospider.cleartext.service.UserService;

@Controller
public class LoginController {

	@Resource
	private UserService userService;

	@RequestMapping(value = { "", "/", "/login" }, method = RequestMethod.GET)
	public String login(ModelMap model, Principal principal) {
		if (principal != null && StringUtils.isNotBlank(principal.getName())) {
			model.put("username", principal.getName());
		}
		return "login";

	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {

		return "login";

	}

}