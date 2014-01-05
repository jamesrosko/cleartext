package com.neospider.cleartext.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.att.api.config.AppConfig;
import com.att.api.immn.service.IMMNService;
import com.att.api.immn.service.Message;
import com.att.api.immn.service.MessageList;
import com.att.api.oauth.OAuthService;
import com.att.api.oauth.OAuthToken;
import com.att.api.rest.RESTException;

@Service
public class LoadMessagesService {
	private static final Logger logger = Logger.getLogger(InappropriateService.class);

	private static final int MESSAGES_TO_LOAD = 20;
	@Resource
	private UserService userService;

	public List<CtMessage> loadMessages(User user) {
		try {
			return getImmnMessages(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@SuppressWarnings("deprecation")
	private List<CtMessage> getImmnMessages(User user) throws Exception {
		AppConfig appConfig = getAppConfig();

		OAuthToken token = getOrCreateToken(user, appConfig);

		IMMNService immnService = new IMMNService(appConfig.getFQDN(), token);
		MessageList messageList = null;
		try {
			immnService.createMessageIndex();
			messageList = immnService.getMessageList(MESSAGES_TO_LOAD, 0);
		} catch (RESTException e) {
			token = createNewToken(user, appConfig);
			immnService.createMessageIndex();
			messageList = immnService.getMessageList(MESSAGES_TO_LOAD, 0);
		}

		List<CtMessage> messages = new ArrayList<CtMessage>();
		List<Message> immnMessages = Arrays.asList(messageList.getMessages());
		for (Message message : immnMessages) {
			String[] recipientsArray = message.getRecipients();
			String recipients = StringUtils.join(recipientsArray, ",");
			CtMessage ctMessage = new CtMessage(message.getText(), message.getFrom(), recipients, message.getTimeStamp());
			messages.add(ctMessage);
		}

		return messages;
	}

	private OAuthToken getOrCreateToken(User user, AppConfig appConfig) throws RESTException {
		OAuthToken token = user.getToken();
		if (token == null) {
			token = createNewToken(user, appConfig);
		}
		return token;
	}

	private OAuthToken createNewToken(User user, AppConfig appConfig) throws RESTException {
		OAuthToken token;
		OAuthService oAuthService = new OAuthService(appConfig.getFQDN(), appConfig.getClientId(),
		    appConfig.getClientSecret());
		String code = user.getCode();
		if (StringUtils.isBlank(code)) {
			throw new RuntimeException("You need an auth code, please Authorize from the device you wish to monitor.");
		}
		token = oAuthService.getTokenUsingCode(code);
		userService.setToken(user.getUsername(), token);
		return token;
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

	protected OAuthToken getToken(String scope) {
		AppConfig cfg = getAppConfig();
		final String clientId = cfg.getClientId();
		final String clientSecret = cfg.getClientSecret();
		final OAuthService service = new OAuthService(cfg.getOauthFQDN(), clientId, clientSecret);
		logger.info("clientId:" + clientId);
		logger.info("clientSecret:" + clientSecret);
		logger.info("scope:" + scope);

		OAuthToken token = null;
		try {
			token = service.getToken(scope);
		} catch (RESTException e) {
			throw new RuntimeException(e);
		}

		return token;
	}
}
