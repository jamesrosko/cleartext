package com.neospider.cleartext.cron;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import com.neospider.cleartext.service.InappropriateService;
import com.neospider.cleartext.service.User;
import com.neospider.cleartext.service.UserService;

public class BackgroundProcessCron {

	private static final Logger logger = Logger.getLogger(BackgroundProcessCron.class);
	@Resource
	private InappropriateService inappropriateService;

	@Resource
	private UserService userService;

	@Scheduled(cron = "*/15 * * * * ?")
	public void runBackgrounProcess() {
		logger.info("runBackgrounProcess. Current time is :: " + new Date());

		List<User> allUser = userService.getAllUser();
		for (User user : allUser) {
			try {
				logger.info("checking user:" + user.getUsername());
				logger.info("user info:" + user);
				inappropriateService.checkForInappropriateMessages(user);
			} catch (Exception e) {
				logger.error("error: " + e.getMessage());
			}
		}
	}
}
