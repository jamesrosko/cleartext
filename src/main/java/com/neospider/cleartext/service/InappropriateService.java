package com.neospider.cleartext.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.att.api.config.AppConfig;
import com.att.api.oauth.OAuthService;
import com.att.api.oauth.OAuthToken;
import com.att.api.rest.RESTException;
import com.att.api.sms.service.SMSService;

@Service
public class InappropriateService {
	private static final Logger logger = Logger.getLogger(InappropriateService.class);

	private static Set<String> messagesSent = new HashSet<String>();

	@Resource
	private LoadMessagesService loadMessagesService;

	@Resource
	private AlertWordsService alertWordsService;

	public InappropriateResult checkForInappropriateMessages(User user) {
		InappropriateResult result = new InappropriateResult();
		boolean foundAlertWord = findAlertWord(user);

		if (foundAlertWord) {
			result.setAbuseFound(true);
			String ownerAddr = "14259193066";
			String monitoringAddr = "14254060395";
			result.setOwnerTelephone(ownerAddr);
			sendSmsToOwner(ownerAddr, monitoringAddr);
		} else {
			result.setAbuseFound(false);
		}

		return result;
	}

	public void sendSmsToOwner(String ownerAddr, String monitoringAddr) {
		AppConfig appConfig = getAppConfig();

		OAuthToken token = getToken("SMS");
		logger.info("got token:" + token.getAccessToken());
		SMSService service = new SMSService(appConfig.getFQDN(), token);
		try {
			String msg = "ClearText found innappropriate txting with the phone " + monitoringAddr;

			logger.info("sending sms to:" + ownerAddr);
			service.sendSMS(ownerAddr, msg, false);
		} catch (RESTException e) {
			throw new RuntimeException(e);
		}
	}

	private AppConfig getAppConfig() {
		AppConfig appConfig = null;
		try {
			appConfig = AppConfig.getInstance();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return appConfig;
	}

	private boolean findAlertWord(User user) {
		List<CtMessage> messages = loadMessagesService.loadMessages(user);
		Set<String> alertWords = alertWordsService.getAlertWordSet(user.getUsername());

		boolean foundAlertWord = false;
		for (CtMessage message : messages) {
			String[] wordArray = message.getMessage().split("\\s");
			Set<String> words = new HashSet<String>();
			for (String word : wordArray) {
				words.add(word.toLowerCase());
			}

			words.retainAll(alertWords);
			for (String word : words) {
				if (!messagesSent.contains(word)) {
					foundAlertWord = true;
					messagesSent.add(word);
				}
			}
		}
		return foundAlertWord;
	}

	protected OAuthToken getToken(String scope) {
		AppConfig cfg = getAppConfig();
		final String clientId = cfg.getClientId();
		final String clientSecret = cfg.getClientSecret();
		final OAuthService service = new OAuthService(cfg.getOauthFQDN(), clientId, clientSecret);

		OAuthToken token = null;
		try {
			token = service.getToken(scope);
		} catch (RESTException e) {
			throw new RuntimeException(e);
		}

		return token;
	}
}
