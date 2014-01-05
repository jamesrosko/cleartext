package com.neospider.cleartext.service;

public class InappropriateResult {

	boolean abuseFound = false;
	String ownerTelephone;

	public boolean isAbuseFound() {
		return abuseFound;
	}

	public void setAbuseFound(boolean abuseFound) {
		this.abuseFound = abuseFound;
	}

	public String getOwnerTelephone() {
		return ownerTelephone;
	}

	public void setOwnerTelephone(String ownerTelephone) {
		this.ownerTelephone = ownerTelephone;
	}
}
