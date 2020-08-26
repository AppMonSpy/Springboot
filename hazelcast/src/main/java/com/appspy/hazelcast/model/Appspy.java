package com.appspy.hazelcast.model;

public class Appspy {

	private String appId;
	private String appName;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Override
	public String toString() {
		return "Appspy [appId=" + appId + ", appName=" + appName + "]";
	}

}