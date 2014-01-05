package com.neospider.cleartext.service;

import java.util.Set;

public class UserAlertWords {
	private Set<String> alertWords;

	public void addAlertWord(String word) {
		String lowerCase = word.toLowerCase();
		if (!alertWords.contains(lowerCase)) {
			alertWords.add(lowerCase);
		}
	}

	public Set<String> getAlertWords() {
		return alertWords;
	}

	public void setAlertWords(Set<String> alertWords) {
		this.alertWords = alertWords;
	}
}
