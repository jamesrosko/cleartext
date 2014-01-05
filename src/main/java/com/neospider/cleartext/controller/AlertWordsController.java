package com.neospider.cleartext.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neospider.cleartext.service.AlertWordsService;
import com.neospider.cleartext.service.User;
import com.neospider.cleartext.service.UserService;

@Controller
@RequestMapping({ "/secure/alertwords" })
public class AlertWordsController {

	public static final String DEFAULT_PAGE = "/alertwords/list";

	private static final Logger logger = Logger.getLogger(AlertWordsController.class);

	@Resource
	private AlertWordsService alertWordsService;

	@Resource
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(ModelMap model, Principal principal) {
		logger.info("callback page get request");

		User user = userService.getOrCreateUser(principal);
		String username = user.getUsername();
		List<String> alertWords = alertWordsService.loadAlertWords(username);

		model.put("username", username);
		model.put("alertWords", alertWords);
		return DEFAULT_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(ModelMap model, @RequestParam String alertWord, Principal principal) {
		logger.info("callback page POST request");

		User user = userService.getOrCreateUser(principal);
		alertWordsService.addAlertWord(user.getUsername(), alertWord);

		model.put("notification", "New Alert Word Added.");

		return doGet(model, principal);
	}

	public AlertWordsService getAlertWordsService() {
		return alertWordsService;
	}

	public void setAlertWordsService(AlertWordsService alertWordsService) {
		this.alertWordsService = alertWordsService;
	}

}
