package com.neospider.cleartext.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neospider.cleartext.service.LoadMessagesService;
import com.neospider.cleartext.service.CtMessage;
import com.neospider.cleartext.service.User;
import com.neospider.cleartext.service.UserService;

@Controller
@RequestMapping({ "/secure/txtlog" })
public class TxtLogController {

	public static final String DEFAULT_PAGE = "/txtlog/list";

	private static final Logger logger = Logger.getLogger(TxtLogController.class);

	@Resource
	private LoadMessagesService loadMessagesService;

	@Resource
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(ModelMap model, Principal principal) {
		logger.info("callback page get request");

		User user = userService.getOrCreateUser(principal);
		String username = user.getUsername();
		model.put("username", username);

		try {
			List<CtMessage> messages = loadMessagesService.loadMessages(user);
			model.put("messages", messages);
		} catch (Exception e) {
			String error = "Could not load the messages.";
			model.put("error", error + " " + e.getMessage());
			logger.error(error + " " + e.getMessage());
		}

		return DEFAULT_PAGE;
	}

	public LoadMessagesService getLoadMessagesService() {
		return loadMessagesService;
	}

	public void setLoadMessagesService(LoadMessagesService loadMessagesService) {
		this.loadMessagesService = loadMessagesService;
	}
}
