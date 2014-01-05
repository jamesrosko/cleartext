package com.neospider.cleartext.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AlertWordsService {

	private static Map<String, UserAlertWords> userAlertWordsMap = new HashMap<String, UserAlertWords>();

	public Set<String> getAlertWordSet(String username) {
		UserAlertWords userAlertWords = userAlertWordsMap.get(username);
		if (userAlertWords == null) {
			userAlertWords = new UserAlertWords();
			HashSet<String> defaultAlertWords = new HashSet<String>();
			defaultAlertWords.add("badword1");
			defaultAlertWords.add("badword2");
			defaultAlertWords.add("badword3");
			userAlertWords.setAlertWords(defaultAlertWords);

			userAlertWordsMap.put(username, userAlertWords);
		}
		return userAlertWords.getAlertWords();
	}

	public List<String> loadAlertWords(String username) {
		return new ArrayList<String>(getAlertWordSet(username));
	}

	public void addAlertWord(String username, String alertWord) {
		String lowerCase = StringUtils.lowerCase(alertWord);
		UserAlertWords userAlertWords = userAlertWordsMap.get(username);
		Set<String> alertWords = userAlertWords.getAlertWords();
		if (alertWords.contains(lowerCase)) {
			return;
		}
		alertWords.add(lowerCase);
		userAlertWords.addAlertWord(lowerCase);
		userAlertWordsMap.put(username, userAlertWords);
	}

}
